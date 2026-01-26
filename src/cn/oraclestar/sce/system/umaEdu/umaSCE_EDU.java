package cn.oraclestar.sce.system.umaEdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.oraclestar.sce.system.UI.fmt.rgb;
import cn.oraclestar.sce.system.UI.indicators.ProgressBar;
import cn.oraclestar.sce.system.umaCore.umaCore_core;
import cn.oraclestar.sce.system.umaCore.umaCore_header;

public class umaSCE_EDU {

    public static class type {
        public static final int speed = 0;
        public static final int stamina = 1;
        public static final int power = 2;
        public static final int guts = 3;
        public static final int wit = 4;
        public static final int sp = 5;
    }

    public static class umaSCE_Card extends umaCore_core {
        public umaSCE_Card(umaCore_header uma) {
            super();
            this.copyFrom(uma);
        }
        
        public int friendship = 0;
        public int trainingType = type.sp;
    }

    public static class EPT {
        public int v5main = 0;
        public int v5fold = 0;
        public int v5sp = 0;
        
        public EPT() {}
        public EPT(int v5main, int v5fold, int v5sp) {
            this.v5main = v5main;
            this.v5fold = v5fold;
            this.v5sp = v5sp;
        }

        @Override
        public String toString() {
            return "{\"main\": " + v5main + ", \"sub\": " + v5fold + ", \"SP\": " + v5sp + "}";
        }
    }

    private static class EptAggregate {
        int count = 0;
        long sumMain = 0;
        long sumFold = 0;
        long sumSp = 0;
        EPT highest = new EPT(0, 0, 0);
        EPT lowest = new EPT(65535, 65535, 65535);
        EPT last = new EPT(0, 0, 0);
    }

    public static class trainingData {
        public int speed = 0;
        public int stamina = 0;
        public int power = 0;
        public int guts = 0;
        public int wit = 0;
        public int sp = 0;
        
        public trainingData() {}
        public trainingData(int s, int st, int p, int g, int w, int sp) {
            this.speed = s; this.stamina = st; this.power = p; this.guts = g; this.wit = w; this.sp = sp;
        }
    }

    public static class trainingBonus {
        public int speed = 0;
        public int stamina = 0;
        public int power = 0;
        public int guts = 0;
        public int wit = 0;
        public int sp = 0;
        public int ph = 0;
        
        public trainingBonus() {}
        public trainingBonus(int s, int st, int p, int g, int w, int sp, int ph) {
            this.speed = s; this.stamina = st; this.power = p; this.guts = g; this.wit = w; this.sp = sp; this.ph = ph;
        }
        
        public trainingBonus(trainingBonus other) {
            this.speed = other.speed;
            this.stamina = other.stamina;
            this.power = other.power;
            this.guts = other.guts;
            this.wit = other.wit;
            this.sp = other.sp;
            this.ph = other.ph;
        }
    }

    public static class umaMain {
        public int friendshipBonus = 0;
        public int moodEffect = 0;
        public int traningEffect = 0;
    }

    private Random rng;
    private ProgressBar bar;

    protected List<umaSCE_Card> cards = new ArrayList<>();
    protected List<trainingData> trainingDataVector = new ArrayList<>();
    protected List<trainingBonus> trainingBonusVector = new ArrayList<>();
    protected List<Integer> clickNum = new ArrayList<>();
    protected List<Integer> trainingLv = new ArrayList<>();
    protected Map<Integer, List<umaSCE_Card>> cardAllocation = new HashMap<>();
    protected List<Integer> sorceEpt = new ArrayList<>();
    protected List<Integer> umaAttr = new ArrayList<>();

    protected EPT eptHighest = new EPT();
    protected EPT eptLowest = new EPT();
    protected EPT eptEx = new EPT();
    protected EPT eptLast = new EPT();

    private long totalMain = 0;
    private long totalFold = 0;
    private long totalSp = 0;
    private int totalCount = 0;

    public umaSCE_EDU() {
        rng = new Random();
        // Initialize lists
        for(int i=0; i<6; i++) trainingDataVector.add(new trainingData());
        for(int i=0; i<5; i++) clickNum.add(0);
        for(int i=0; i<5; i++) trainingLv.add(1);
        for(int i=0; i<6; i++) cardAllocation.put(i, new ArrayList<>());
        for(int i=0; i<5; i++) sorceEpt.add(0);
        
        // umaAttr init: { 100,100,100,100,100,300 }
        umaAttr.add(100); umaAttr.add(100); umaAttr.add(100); umaAttr.add(100); umaAttr.add(100); umaAttr.add(300);

        // ProgressBar init
        bar = new ProgressBar(
            25, "[", "=", ">", " ", "]", 
            new rgb(255, 255, 255), true, true, true
        );
    }
    
    public EPT pushHighestEpt() { return eptHighest; }
    public EPT pushLowestEpt() { return eptLowest; }
    public EPT pushExEpt() { return eptEx; }

    public boolean initialize(umaCore_header uma) {
        cards.clear();
        //将玩家的支援卡加入队列中
        umaSCE_Card pCard = new umaSCE_Card(uma);
        pCard.friendship = pCard.pushRate("initalFriendship");
        cards.add(pCard);

        //插入其他支援卡
        {
            umaCore_core umaTemp = new umaCore_core();

            umaTemp.getAttributes("", type.speed, 30, 20, 10, 30, 65, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0);
            umaSCE_Card sCard = new umaSCE_Card(umaTemp);
            sCard.friendship = sCard.pushRate("initalFriendship");
            cards.add(sCard);

            umaTemp.getAttributes("", type.stamina, 30, 20, 10, 30, 65, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0);
            umaSCE_Card sCard1 = new umaSCE_Card(umaTemp);
            sCard1.friendship = sCard1.pushRate("initalFriendship");
            cards.add(sCard1);

            umaTemp.getAttributes("", type.power, 30, 20, 10, 30, 65, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0);
            umaSCE_Card sCard2 = new umaSCE_Card(umaTemp);
            sCard2.friendship = sCard2.pushRate("initalFriendship");
            cards.add(sCard2);

            umaTemp.getAttributes("", type.guts, 30, 20, 10, 30, 65, 0, 0, 0, 0, 0, 1, 0, 1, 2, 0, 0);
            umaSCE_Card sCard4 = new umaSCE_Card(umaTemp);
            sCard4.friendship = sCard4.pushRate("initalFriendship");
            cards.add(sCard4);

            umaTemp.getAttributes("", type.wit, 30, 20, 10, 30, 65, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 1);
            umaSCE_Card sCard3 = new umaSCE_Card(umaTemp);
            sCard3.friendship = sCard3.pushRate("initalFriendship");
            cards.add(sCard3);
        }

        //计算v3，后续使用
        for (umaSCE_Card card : cards) {
            card.evalV3();
        }

        //分配场地基础属性加成 - Lv1
        trainingBonusVector.clear();
        trainingBonusVector.add(new trainingBonus(11, 0, 2, 0, 0, 7, -20));
        trainingBonusVector.add(new trainingBonus(0, 8, 0, 6, 0, 7, -21));
        trainingBonusVector.add(new trainingBonus(0, 4, 10, 0, 0, 7, -21));
        trainingBonusVector.add(new trainingBonus(2, 0, 2, 10, 0, 7, -21));
        trainingBonusVector.add(new trainingBonus(3, 0, 0, 0, 7, 5, 5));

        //初始化马娘属性
        umaAttr.set(0, 100); umaAttr.set(1, 100); umaAttr.set(2, 100); umaAttr.set(3, 100); umaAttr.set(4, 100); umaAttr.set(5, 300);

        //
        for (trainingData tr : trainingDataVector) {
            tr.speed = 0; tr.stamina = 0; tr.power = 0; tr.guts = 0; tr.wit = 0; tr.sp = 0;
        }

        //训练场点击次数归零
        for (int i = 0; i < clickNum.size(); i++) {
            clickNum.set(i, 0);
        }

        //初始化训练场等级
        for (int i = 0; i < trainingLv.size(); i++) {
            trainingLv.set(i, 1);
        }

        return true;
    }

    private void tidy() {
        //清空场地收益
        for (trainingData tr : trainingDataVector) {
            tr.speed = 0; tr.stamina = 0; tr.power = 0; tr.guts = 0; tr.wit = 0; tr.sp = 0;
        }
        //清除支援卡位置信息
        for (List<umaSCE_Card> list : cardAllocation.values()) {
            list.clear();
        }
        //清空评分
        for (int i = 0; i < sorceEpt.size(); i++) {
            sorceEpt.set(i, 0);
        }
    }

    private void roundLoop() {
        allocate();
        //计算每个场地的收益
        pretreatment();
        //计算场地加权评分
        score();
        //模拟点击
        simulateClick();
    }

    private void allocate() {
        for (umaSCE_Card card : cards) {
            float r = rng.nextFloat();
            if (r <= card.pushEpt("specialtyRate")) {
                //暴击训练
                card.trainingType = card.pushType();
            } else if (r > card.pushEpt("specialtyRate") && r <= (card.pushEpt("unspecialtyRate") * 4)) {
                //除自己的type外的其他训练（随机）
                int t = card.pushType();
                while (card.trainingType == t) {
                    card.trainingType = rng.nextInt(5);
                }
            } else {
                card.trainingType = type.sp;
            }
        }
        //将分配的信息写入cardAllocation
        for (umaSCE_Card card : cards) {
            cardAllocation.get(card.trainingType).add(card);
        }
    }

    private void pretreatment() {
        for (Map.Entry<Integer, List<umaSCE_Card>> entry : cardAllocation.entrySet()) {
            int areaType = entry.getKey();
            List<umaSCE_Card> areaCards = entry.getValue();

            if (areaType != type.sp) {
                //整合属性加成
                trainingBonus bonus = new trainingBonus(trainingBonusVector.get(areaType));
                for (umaSCE_Card card : cards) {
                    bonus.speed += card.pushRate("speed");
                    bonus.stamina += card.pushRate("stamina");
                    bonus.power += card.pushRate("power");
                    bonus.guts += card.pushRate("guts");
                    bonus.wit += card.pushRate("wit");
                    bonus.sp += card.pushRate("sp");
                }
                //整合友情干劲训练加成
                umaMain umaMainTemp = new umaMain();
                for (umaSCE_Card card : areaCards) {
                    if (card.friendship > 79) {
                        int temp = card.pushRate("friendshipBonus");
                        float r = temp * 0.01f + 1.0f;
                        float umaR = umaMainTemp.friendshipBonus * 0.01f + 1.0f;
                        
                        umaMainTemp.friendshipBonus = (int)((r * umaR - 1.0f) * 100.0f);
                    }
                    umaMainTemp.moodEffect += card.pushRate("moodEffect");
                    umaMainTemp.traningEffect += card.pushRate("traningEffect");
                }

                umaCore_core umaTemp = new umaCore_core();
                umaTemp.getAttributes(
                    "", areaType,
                    umaMainTemp.friendshipBonus, umaMainTemp.moodEffect, umaMainTemp.traningEffect, 0, 0,
                    0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0
                );

                //计算主收益倍率 和 副收益倍率
                umaTemp.evalV1();

                //计算收益
                switch (areaType) {
                case 0:
                    trainingDataVector.get(areaType).speed = (int)(bonus.speed * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).power = (int)(bonus.power * umaTemp.pushEpt("friendshipRate"));
                    break;
                case 1:
                    trainingDataVector.get(areaType).stamina = (int)(bonus.stamina * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).guts = (int)(bonus.guts * umaTemp.pushEpt("friendshipRate"));
                    break;
                case 2:
                    trainingDataVector.get(areaType).power = (int)(bonus.power * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).speed = (int)(bonus.stamina * umaTemp.pushEpt("friendshipRate")); // Note: C++ code says bonus.stamina here for speed? "trainingDataVector[area.first].speed = bonus.stamina * ..." - keeping as is.
                    break;
                case 3:
                    trainingDataVector.get(areaType).power = (int)(bonus.power * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).speed = (int)(bonus.speed * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).guts = (int)(bonus.guts * umaTemp.pushEpt("friendshipRate"));
                    break;
                case 4:
                    trainingDataVector.get(areaType).speed = (int)(bonus.speed * umaTemp.pushEpt("friendshipRate"));
                    trainingDataVector.get(areaType).wit = (int)(bonus.wit * umaTemp.pushEpt("friendshipRate"));
                    break;
                default:
                    break;
                }
                trainingDataVector.get(areaType).sp = (int)(bonus.sp * umaTemp.pushEpt("friendshipRate"));
            }
        }
    }

    private void score() {
        for (int i = 0; i < sorceEpt.size(); i++) {
            sorceEpt.set(i, 
                trainingDataVector.get(i).speed +
                trainingDataVector.get(i).stamina +
                trainingDataVector.get(i).power +
                trainingDataVector.get(i).guts +
                trainingDataVector.get(i).wit +
                trainingDataVector.get(i).sp
            );
        }
    }

    private void simulateClick() {
        int index = 0;
        int maxEpt = 0;
        for (int i = 0; i < sorceEpt.size(); i++) {
            int ept = sorceEpt.get(i);
            if (ept > maxEpt) {
                maxEpt = ept;
                index = i;
            }
        }
        //点击对应的训练场地
        umaAttr.set(type.speed, umaAttr.get(type.speed) + trainingDataVector.get(index).speed);
        umaAttr.set(type.stamina, umaAttr.get(type.stamina) + trainingDataVector.get(index).stamina);
        umaAttr.set(type.power, umaAttr.get(type.power) + trainingDataVector.get(index).power);
        umaAttr.set(type.guts, umaAttr.get(type.guts) + trainingDataVector.get(index).guts);
        umaAttr.set(type.wit, umaAttr.get(type.wit) + trainingDataVector.get(index).wit);
        umaAttr.set(type.sp, umaAttr.get(type.sp) + trainingDataVector.get(index).sp);

        //此训练场所有支援卡羁绊+5
        for (umaSCE_Card card : cardAllocation.get(index)) {
            card.friendship += 5;
        }

        clickNum.set(index, clickNum.get(index) + 1);
    }

    private void roundTidy() {
        for (trainingData tr : trainingDataVector) {
            tr.speed = 0; tr.stamina = 0; tr.power = 0; tr.guts = 0; tr.wit = 0; tr.sp = 0;
        }
        //训练场升级(直接覆盖)
        if (clickNum.get(type.speed) > 0 && clickNum.get(type.speed) % 5 == 0) {
            if (trainingLv.get(type.speed) == 1) {
                trainingBonusVector.get(type.speed).speed = 12;
                trainingBonusVector.get(type.speed).ph = -22;
                trainingLv.set(type.speed, 2);
            } else if (trainingLv.get(type.speed) == 2) {
                trainingBonusVector.get(type.speed).speed = 13;
                trainingBonusVector.get(type.speed).ph = -23;
                trainingBonusVector.get(type.speed).power = 3;
                trainingLv.set(type.speed, 3);
            } else if (trainingLv.get(type.speed) == 3) {
                trainingBonusVector.get(type.speed).speed = 14;
                trainingBonusVector.get(type.speed).ph = -25;
                trainingLv.set(type.speed, 3); // Note: C++ code sets to 3 again? "trainingLv[type::speed] = 3;" - keeping as is.
            } else if (trainingLv.get(type.speed) == 4) {
                trainingBonusVector.get(type.speed).speed = 15;
                trainingBonusVector.get(type.speed).ph = -26;
                trainingBonusVector.get(type.speed).power = 4;
                trainingLv.set(type.speed, 5);
            }
        }
        if (clickNum.get(type.stamina) > 0 && clickNum.get(type.stamina) % 5 == 0) {
            if (trainingLv.get(type.stamina) == 1) {
                trainingBonusVector.get(type.stamina).stamina = 9;
                trainingBonusVector.get(type.stamina).ph = -23;
                trainingLv.set(type.stamina, 2);
            } else if (trainingLv.get(type.stamina) == 2) {
                trainingBonusVector.get(type.stamina).stamina = 10;
                trainingBonusVector.get(type.stamina).guts = 7;
                trainingBonusVector.get(type.stamina).ph = -24;
                trainingLv.set(type.stamina, 3);
            } else if (trainingLv.get(type.stamina) == 3) {
                trainingBonusVector.get(type.stamina).stamina = 11;
                trainingBonusVector.get(type.stamina).ph = -26;
                trainingLv.set(type.stamina, 4);
            } else if (trainingLv.get(type.stamina) == 4) {
                trainingBonusVector.get(type.stamina).stamina = 12;
                trainingBonusVector.get(type.stamina).guts = 8;
                trainingBonusVector.get(type.stamina).ph = -27;
                trainingLv.set(type.stamina, 5);
            }
        }
        
        if (clickNum.get(type.power) > 0 && clickNum.get(type.power) % 5 == 0) {
            if (trainingLv.get(type.power) == 1) {
                trainingBonusVector.get(type.power).power = 11;
                trainingBonusVector.get(type.power).ph = -23;
                trainingLv.set(type.power, 2);
            } else if (trainingLv.get(type.power) == 2) {
                trainingBonusVector.get(type.power).power = 12;
                trainingBonusVector.get(type.power).stamina = 5;
                trainingBonusVector.get(type.power).ph = -24;
                trainingLv.set(type.power, 3);
            } else if (trainingLv.get(type.power) == 3) {
                trainingBonusVector.get(type.power).power = 13;
                trainingBonusVector.get(type.power).ph = -26;
                trainingLv.set(type.power, 4);
            } else if (trainingLv.get(type.power) == 4) {
                trainingBonusVector.get(type.power).power = 14;
                trainingBonusVector.get(type.power).stamina = 6;
                trainingBonusVector.get(type.power).ph = -27;
                trainingLv.set(type.power, 5);
            }
        }
        if (clickNum.get(type.guts) > 0 && clickNum.get(type.guts) % 5 == 0) {
            if (trainingLv.get(type.guts) == 1) {
                trainingBonusVector.get(type.guts).guts = 11;
                trainingBonusVector.get(type.guts).ph = -23;
                trainingLv.set(type.guts, 2);
            } else if (trainingLv.get(type.guts) == 2) {
                trainingBonusVector.get(type.guts).guts = 12;
                trainingBonusVector.get(type.guts).ph = -24;
                trainingLv.set(type.guts, 3);
            } else if (trainingLv.get(type.guts) == 3) {
                trainingBonusVector.get(type.guts).guts = 13;
                trainingBonusVector.get(type.guts).ph = -26;
                trainingLv.set(type.guts, 4);
            } else if (trainingLv.get(type.guts) == 4) {
                trainingBonusVector.get(type.guts).guts = 14;
                trainingBonusVector.get(type.guts).speed = 3;
                trainingBonusVector.get(type.guts).power = 3;
                trainingBonusVector.get(type.guts).ph = -27;
                trainingLv.set(type.guts, 5);
            }
        }
        if (clickNum.get(type.wit) > 0 && clickNum.get(type.wit) % 5 == 0) {
            if (trainingLv.get(type.wit) == 1) {
                trainingBonusVector.get(type.wit).wit = 8;
                trainingLv.set(type.wit, 2);
            } else if (trainingLv.get(type.wit) == 2) {
                trainingBonusVector.get(type.wit).wit = 9;
                trainingBonusVector.get(type.wit).speed = 4;
                trainingLv.set(type.wit, 3);
            } else if (trainingLv.get(type.wit) == 3) {
                trainingBonusVector.get(type.wit).wit = 10;
                trainingLv.set(type.wit, 4);
            } else if (trainingLv.get(type.wit) == 4) {
                trainingBonusVector.get(type.wit).wit = 11;
                trainingBonusVector.get(type.wit).speed = 5;
                trainingLv.set(type.wit, 5);
            }
        }

        //随机事件（现在为空）
    }

    private void evalV5(int simNum) {
        updateEptStats(evaluateCurrentEpt(), simNum);
    }

    public boolean run(umaCore_header uma, int loop) {
        return runMultithread(uma, loop);
    }

    public boolean runMultithread(umaCore_header uma, int loop) {
        return runMultithread(uma, loop, Runtime.getRuntime().availableProcessors());
    }

    public boolean runMultithread(umaCore_header uma, int loop, int threadCount) {
        resetEptStats();

        int threads = Math.max(1, threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<EptAggregate>> futures = new ArrayList<>();

        bar.set_progress(0);

        int batchSize = Math.max(1, (int)Math.ceil(loop / (threads * 4.0))); // coarser tasks keep CPUs busy
        int submitted = 0;
        while (submitted < loop) {
            final int runCount = Math.min(batchSize, loop - submitted);
            futures.add(executor.submit(new Callable<EptAggregate>() {
                @Override
                public EptAggregate call() {
                    umaSCE_EDU sim = new umaSCE_EDU();
                    EptAggregate agg = new EptAggregate();
                    for (int i = 0; i < runCount; i++) {
                        EPT ept = sim.simulateOnce(uma);
                        agg.count++;
                        agg.sumMain += ept.v5main;
                        agg.sumFold += ept.v5fold;
                        agg.sumSp += ept.v5sp;
                        if (ept.v5main + ept.v5fold > agg.highest.v5main + agg.highest.v5fold) {
                            agg.highest = ept;
                        }
                        if (agg.lowest.v5main == 65535 || ept.v5main + ept.v5fold < agg.lowest.v5main + agg.lowest.v5fold) {
                            agg.lowest = ept;
                        }
                        agg.last = ept;
                    }
                    return agg;
                }
            }));
            submitted += runCount;
        }

        int processed = 0;
        float lastProgress = 0f;
        try {
            for (Future<EptAggregate> future : futures) {
                EptAggregate result = future.get();
                mergeAggregate(result);
                processed += result.count;
                float progress = (float)processed / loop * 100;
                if (progress - lastProgress >= 0.5f || processed == loop) {
                    bar.set_progress(progress);
                    lastProgress = progress;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            executor.shutdownNow();
            bar.set_progress(100);
        }
        return true;
    }

    private void resetEptStats() {
        eptHighest = new EPT(0, 0, 0);
        eptLowest = new EPT(65535, 65535, 65535);
        eptEx = new EPT(0, 0, 0);
        eptLast = new EPT(0, 0, 0);
        totalMain = 0;
        totalFold = 0;
        totalSp = 0;
        totalCount = 0;
    }

    private EPT simulateOnce(umaCore_header uma) {
        initialize(uma);
        for (int i = 0; i < 72; i++) {
            tidy();
            roundLoop();
            roundTidy();
        }
        return evaluateCurrentEpt();
    }

    private EPT evaluateCurrentEpt() {
        EPT eptTemp = new EPT();
        switch (cards.get(0).pushType()) {
        case 0:
            eptTemp.v5main = umaAttr.get(type.speed) + umaAttr.get(type.power);
            eptTemp.v5fold = umaAttr.get(type.stamina) + umaAttr.get(type.guts) + umaAttr.get(type.wit);
            eptTemp.v5sp = umaAttr.get(type.sp);
            break;
        case 1:
            eptTemp.v5main = umaAttr.get(type.stamina) + umaAttr.get(type.guts);
            eptTemp.v5fold = umaAttr.get(type.speed) + umaAttr.get(type.power) + umaAttr.get(type.wit);
            eptTemp.v5sp = umaAttr.get(type.sp);
            break;
        case 2:
            eptTemp.v5main = umaAttr.get(type.power) + umaAttr.get(type.stamina);
            eptTemp.v5fold = umaAttr.get(type.speed) + umaAttr.get(type.guts) + umaAttr.get(type.wit);
            eptTemp.v5sp = umaAttr.get(type.sp);
            break;
        case 3:
            eptTemp.v5main = umaAttr.get(type.speed) + umaAttr.get(type.power) + umaAttr.get(type.guts);
            eptTemp.v5fold = umaAttr.get(type.stamina) + umaAttr.get(type.wit);
            eptTemp.v5sp = umaAttr.get(type.sp);
            break;
        case 4:
            eptTemp.v5main = umaAttr.get(type.wit) + umaAttr.get(type.speed);
            eptTemp.v5fold = umaAttr.get(type.power) + umaAttr.get(type.guts) + umaAttr.get(type.wit);
            eptTemp.v5sp = umaAttr.get(type.sp);
            break;
        default:
            break;
        }
        return eptTemp;
    }

    private void updateEptStats(EPT eptTemp, int simNum) {
        totalCount++;
        totalMain += eptTemp.v5main;
        totalFold += eptTemp.v5fold;
        totalSp += eptTemp.v5sp;
        eptEx.v5main = (int)(totalMain / totalCount);
        eptEx.v5fold = (int)(totalFold / totalCount);
        eptEx.v5sp = (int)(totalSp / totalCount);

        if (eptTemp.v5main + eptTemp.v5fold > eptHighest.v5main + eptHighest.v5fold) {
            eptHighest = eptTemp;
        }
        if (eptLowest.v5main == 65535 || eptTemp.v5main + eptTemp.v5fold < eptLowest.v5main + eptLowest.v5fold) {
            eptLowest = eptTemp;
        }
        eptLast = eptTemp;
    }

    private void mergeAggregate(EptAggregate agg) {
        if (agg == null || agg.count == 0) {
            return;
        }

        totalCount += agg.count;
        totalMain += agg.sumMain;
        totalFold += agg.sumFold;
        totalSp += agg.sumSp;
        eptEx.v5main = (int)(totalMain / totalCount);
        eptEx.v5fold = (int)(totalFold / totalCount);
        eptEx.v5sp = (int)(totalSp / totalCount);

        if (agg.highest.v5main + agg.highest.v5fold > eptHighest.v5main + eptHighest.v5fold) {
            eptHighest = agg.highest;
        }
        if (eptLowest.v5main == 65535 || agg.lowest.v5main + agg.lowest.v5fold < eptLowest.v5main + eptLowest.v5fold) {
            eptLowest = agg.lowest;
        }
        eptLast = agg.last;
    }
}