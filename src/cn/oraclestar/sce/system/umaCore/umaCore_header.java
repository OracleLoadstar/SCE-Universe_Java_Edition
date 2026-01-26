package cn.oraclestar.sce.system.umaCore;

/*
################################################
#                                              #
#        Another:Lat-SKY                       #
#        mofity by xingguangcuican6666         #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

abstract public class umaCore_header {
    protected int id = -1;
    protected Basic _basic;
    protected Rate _rate;
    protected Unique _unique;
    protected Bonus _bonus;
    protected Ept _ept;
    //特殊属性
    public class Basic{
        public Basic(){
            this.name = "Unnamed";
            this.type = 0;
        }
        public Basic(Basic other){
            this.name = other.name;
            this.type = other.type;
            
        }
        public String name = "Unnamed";
        public int type =0;
    }
    public class Rate{
        public Rate(){
            this.friendshipBonus = 0;
            this.moodEffect = 0;
            this.traningEffect = 0;
            this.initalFriendship = 0;
            this.specialtyPriority = 0;
        }
        public Rate(Rate other){
            this.friendshipBonus = other.friendshipBonus;
            this.moodEffect = other.moodEffect;
            this.traningEffect = other.traningEffect;
            this.initalFriendship = other.initalFriendship;
            this.specialtyPriority = other.specialtyPriority;
        }
        public int friendshipBonus = 0;
        public int moodEffect = 0;
        public int traningEffect = 0;
        public int initalFriendship = 0;
        public int specialtyPriority = 0;
    }
    public class Unique{
        public Unique(){
            this.friendshipBonus = 0;
            this.moodEffect = 0;
            this.traningEffect = 0;
            this.initalFriendship = 0;
            this.specialtyPriority = 0;
        }
        public Unique(Unique other){
            this.friendshipBonus = other.friendshipBonus;
            this.moodEffect = other.moodEffect;
            this.traningEffect = other.traningEffect;
            this.initalFriendship = other.initalFriendship;
            this.specialtyPriority = other.specialtyPriority;
        }
        public int friendshipBonus = 0;
        public int moodEffect = 0;
        public int traningEffect = 0;
        public int initalFriendship = 0;
        public int specialtyPriority = 0;
    }
    public class Bonus{
        public Bonus(){
            this.speed = 0;
            this.stamina = 0;
            this.power = 0;
            this.guts = 0;
            this.wit = 0;
            this.sp = 0;
        }
        public Bonus(Bonus other){
            this.speed = other.speed;
            this.stamina = other.stamina;
            this.power = other.power;
            this.guts = other.guts;
            this.wit = other.wit;
            this.sp = other.sp;
        }
        public int speed = 0; 
        public int stamina = 0;
        public int power = 0;
        public int guts = 0;
        public int wit=0;
        public int sp =0;
    }
    public class Ept{
        public Ept(){
            this.friendshipRate = 0.0f;
            this.nonSpecialtyRate = 0.0f;
            this.allFriendshipRate = 0.0f;
            this.allNonSpecialtyRate = 0.0f;
            this.nonSpecialtySpRate = 0.0f;
            this.specialtySpRate = 0.0f;
            this.specialtyRate = 0.0f;
            this.unspecialtyRate = 0.0f;
            this.nonTrainingRate = 0.0f;
            this.failureRate = 0.0f;
            this.v3 = 0;
            this.v4main = 0;
            this.v4fold = 0;
            this.v4sp = 0;
        }
        public Ept(Ept other){
            this.friendshipRate = other.friendshipRate;
            this.nonSpecialtyRate = other.nonSpecialtyRate;
            this.allFriendshipRate = other.allFriendshipRate;
            this.allNonSpecialtyRate = other.allNonSpecialtyRate;
            this.nonSpecialtySpRate = other.nonSpecialtySpRate;
            this.specialtySpRate = other.specialtySpRate;
            this.specialtyRate = other.specialtyRate;
            this.unspecialtyRate = other.unspecialtyRate;
            this.nonTrainingRate = other.nonTrainingRate;
            this.failureRate = other.failureRate;
            this.v3 = other.v3;
            this.v4main = other.v4main;
            this.v4fold = other.v4fold;
            this.v4sp = other.v4sp;
        }
        //基础友情训练倍率(v1_ept)
        public float friendshipRate = 0.0f;
        //基础训练倍率(unstrike_v1_ept)
        public float nonSpecialtyRate = 0.0f;
        //总友情训练倍率 含属性加成(v2_ept/strike_v2_ept)
        public float allFriendshipRate = 0.0f;
        //总训练倍率 含属性加成(unstrike_v2_ept)
        public float allNonSpecialtyRate = 0.0f;
        
        //基础SP加成（v2）不包含友情加成，仅计算了单个逛街训练
        public float nonSpecialtySpRate = 0.0f;
        //友情训练SP加成（v2）
        public float specialtySpRate = 0.0f;
        
        //真实得意训练率(strike_rate)
        public float specialtyRate = 0.0f;
        //逛街训练率(unstrike_rate)
        public float unspecialtyRate = 0.0f;
        //逃训率(v3)
        public float nonTrainingRate = 0.0f;
        
        //失效率(failure_rate)
        public float failureRate = 0.0f;
        
        public int v3 = 0;
        
        public int v4main = 0;
        public int v4fold = 0;
        public int v4sp = 0;
    }
    //构造函数 -> umaSCE_Main() = default;
    public umaCore_header(){};
    //析构函数 -> ~umaSCE_Main() = default;
    //释放属性
    abstract public void destory();
    //复制构造函数 -> umaSCE_Main(const umaSCE_Main& other) = default;
    public umaCore_header(umaCore_header other){
        if(other == null){return;}
        this.id = other.id;
        this._basic = new Basic(other._basic);
        this._rate = new Rate(other._rate);
        this._unique = new Unique(other._unique);
        this._bonus = new Bonus(other._bonus);
        this._ept = new Ept(other._ept);
    };
    //-> umaSCE_Main& operator=(const umaSCE_Main& other) = default;
    public void copyFrom(umaCore_header other){
        if(other == null){
            return;
        }
        this.id =other.id;
        this._basic = new Basic(other._basic);
        this._rate =new Rate(other._rate);
        this._unique = new Unique(other._unique);
        this._bonus = new Bonus(other._bonus);
        this._ept = new Ept(other._ept);
    }
    //basic rate uniqe bonus
    abstract public boolean getAttributes(String name,int type,int rate_friendshipBonus,int rate_moodEffect,int rate_traningEffect,int rate_initalFriendship,int rate_specialtyPriority,int unique_friendshipBonus,int unique_moodEffect,int unique_traningEffect,int unique_initalFriendship,int unique_specialtyPriority,int speed,int stamina,int power,int guts,int wit,int sp);
    //basic rate uniqe bonus
    abstract public void pushAttributes(Basic basic,Rate rate,Unique unique,Bonus bonus);
    abstract public void pushEpt(Ept ept);
    //检查类是否有意外的值
    abstract public boolean checked();
    
    //计算
    //-> void evalV1() noexcept;
    //-> void pullEvalV1(float& friendshipRate,float& nonSpecialtyRate) noexcept;
    //-> bool evalV2();
    //-> void pullEvalV2(float& allFriendshipRate);
    //-> bool evalV3();
    //-> void pullEvalV3(float& specialtyRate, float& unspecialtyRate, float& nonTrainingRate);
    //-> bool evalV4();
    //-> void pullEvalV4(int& v4main, int& v4fold, int& v4sp);
    abstract public void evalV1();
    abstract public void pullEvalV1(float friendshipRate,float nonSpecialtyRate);
    abstract public boolean evalV2();
    abstract public void pullEvalV2(float allFriendshipRate);
    abstract public boolean evalV3();
    abstract public void pullEvalV3(float specialtyRate, float unspecialtyRate, float nonTrainingRate);
    abstract public boolean evalV4();
    abstract public void pullEvalV4(int v4main, int v4fold, int v4sp);
    
    //推送单个属性
    
    //-> void pushName(std::string& name) noexcept { name = _basic.name; }
    //-> std::string pushName() const noexcept { return _basic.name; }
    public void pushName(String name){name = _basic.name;};
    public String pushName(){return _basic.name;};
    
    //-> void pushType(int type) const noexcept { type = _basic.type; }
    //-> int pushType() const noexcept { return _basic.type; }
    public void pushType(int type){type = _basic.type;};
    public int pushType(){return _basic.type;};
    
    //-> void pushRate(const std::string& attr, int& carrier) const noexcept;
    //-> int pushRate(const std::string& attr) const noexcept;
    abstract public void pushRate(String attr,int carrier);
    abstract public int pushRate(String attr);
    
    //-> void pushUnique(const std::string& attr, int& carrier) const noexcept;
    //-> int pushUnique(const std::string& attr) const noexcept;
    abstract public void pushUnique(String attr,int carrier);
    abstract public int pushUnique(String attr);
    
    //-> void pushBonus(const std::string& attr, int& carrier) const noexcept;
    //-> int pushBonus(const std::string& attr) const noexcept;
    abstract public void pushBonus(String attr,int carrier);
    abstract public int pushBonus(String attr);
    
    //-> void pushEpt(const std::string& attr, float& carrier) const noexcept;
    //-> void pushEpt(const std::string& attr, int& carrier) const noexcept;
    //-> float pushEpt(const std::string& attr) const noexcept;
    //-> int pushIntEpt(const std::string& attr) const noexcept;
    abstract public void pushEpt(String attr,float carrier);
    abstract public void pushEpt(String attr,int carrier);
    abstract public float pushEpt(String attr);
    abstract public int pushIntEpt(String attr);
    
    //-> void pushId(unsigned int& id) const noexcept { id = this->id; };
    //-> unsigned int pushId() const noexcept { return this->id; };
    public void pushId(int id){id = this.id;};
    public int pushId(){return this.id;};
    
    //获取单个属性
    //-> void getName(const std::string& name) noexcept 
    //-> void getType(int type) noexcept { _basic.type = type; }
    //-> void getRate(const std::string& attr, const int& value);
    //-> void getUnique(const std::string& attr, const int& value);
    //-> void getBonus(const std::string& attr, const int& value);
    //-> void getId(unsigned int id) noexcept { this->id = id; };
    public void getName(String name) 
    { 
        _basic.name = name; 
    };
    public void getType(int type) { _basic.type = type; }
    abstract public void getRate(String attr, int value) throws Exception;
    abstract public void getUnique(String attr, int value) throws Exception;
    abstract public void getBonus(String attr, int value) throws Exception;
    public void getId(int id) { this.id = id; };
}
