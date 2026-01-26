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

public class umaCore_core extends umaCore_header {
    
    public umaCore_core() {
        _basic = new Basic();
        _rate = new Rate();
        _unique = new Unique();
        _bonus = new Bonus();
        _ept = new Ept();
    }
    
    public boolean getAttributes(String name,int type,int rate_friendshipBonus,int rate_moodEffect,int rate_traningEffect,int rate_initalFriendship,int rate_specialtyPriority,int unique_friendshipBonus,int unique_moodEffect,int unique_traningEffect,int unique_initalFriendship,int unique_specialtyPriority,int speed,int stamina,int power,int guts,int wit,int sp)
    {
        try
        {
            _basic.name = name;
            _basic.type = type;
            _rate.friendshipBonus = rate_friendshipBonus;
            _rate.initalFriendship = rate_initalFriendship;
            _rate.moodEffect = rate_moodEffect;
            _rate.specialtyPriority = rate_specialtyPriority;
            _rate.traningEffect = rate_traningEffect;
            _unique.friendshipBonus = unique_friendshipBonus;
            _unique.initalFriendship = unique_initalFriendship;
            _unique.moodEffect = unique_moodEffect;
            _unique.specialtyPriority = unique_specialtyPriority;
            _unique.traningEffect = unique_traningEffect;
            _bonus.speed = speed;
            _bonus.stamina = stamina;
            _bonus.power = power;
            _bonus.guts = guts;
            _bonus.wit = wit;
            _bonus.sp = sp;
        }
        catch (Exception err)
        {
            
            System.err.println("Error: " + err);
            return false;
        }
        return true;
    }
    
    public void pushAttributes(Basic basic,Rate rate,Unique unique,Bonus bonus) 
    {
        basic = _basic;
        rate = _rate;
        unique = _unique;
        bonus = _bonus;
    }
    
    public void pushEpt(Ept ept)
    {
        ept = _ept;
    }
    
    public void destory()
    {
        if (_basic != null) {
            _basic.name = "未命名";
            _basic.type = 0;
        };
        if (_bonus != null){
            _bonus.guts = 0;
            _bonus.power = 0;
            _bonus.sp = 0;
            _bonus.speed = 0;
            _bonus.stamina = 0;
            _bonus.wit = 0;
        }
        if (_rate != null){
            _rate.friendshipBonus = 0;
            _rate.initalFriendship = 0;
            _rate.moodEffect = 0;
            _rate.specialtyPriority = 0;
            _rate.traningEffect = 0;
        };
        if (_unique != null){
            _unique.friendshipBonus = 0;
            _unique.initalFriendship = 0;
            _unique.moodEffect = 0;
            _unique.specialtyPriority = 0;
            _unique.traningEffect = 0;
        }
        
    }
    
    public boolean checked()
    {
        if (_basic.type > 4 || 
            _basic.type < 0)
        {
            return false;
        }
        else if (_rate.friendshipBonus == 65535 ||
            _rate.initalFriendship == 65535 || 
            _rate.moodEffect == 65535 || 
            _rate.specialtyPriority == 65535 || 
            _rate.traningEffect == 65535)
        {
            return false;
        }
        else if (_unique.friendshipBonus == 65535 ||
            _unique.initalFriendship == 65535 ||
            _unique.moodEffect == 65535 ||
            _unique.specialtyPriority == 65535 ||
            _unique.traningEffect == 65535)
        {
            return false;
        }
        else if (_bonus.guts == 65535 ||
            _bonus.power == 65535 ||
            _bonus.sp == 65535 ||
            _bonus.speed == 65535 ||
            _bonus.stamina == 65535 ||
            _bonus.wit == 65535)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
                    
    public void evalV1()
    {
        //v1_ept
        _ept.friendshipRate =
        (_rate.friendshipBonus * 0.01f + 1.0f) *
        (_unique.friendshipBonus * 0.01f + 1.0f) *
        ((_rate.traningEffect + _unique.traningEffect) * 0.01f + 1.0f) *
        ((_rate.moodEffect + _unique.moodEffect) * 0.02f + 1.0f);
        //unstrike_v1_ept
        _ept.nonSpecialtyRate =
        ((_rate.traningEffect + _unique.traningEffect) * 0.01f + 1.0f) *
        ((_rate.moodEffect + _unique.moodEffect) * 0.02f + 1.0f);
    }
    
    public void pullEvalV1(float friendshipRate, float nonSpecialtyRate)
    {
        evalV1();
        friendshipRate = _ept.friendshipRate;
        nonSpecialtyRate = _ept.nonSpecialtyRate;
    }
    
    public boolean evalV2()
    {
        evalV1();
        int bonus;
        switch (_basic.type)
        {
            case 0:
            bonus = _bonus.speed;
            break;
            case 1:
            bonus = _bonus.stamina;
            break;
            case 2:
            bonus = _bonus.power;
            break;
            case 3:
            bonus = _bonus.guts;
            break;
            case 4:
            bonus = _bonus.wit;
            break;
            default:
            _ept.allFriendshipRate = -1.0f;
            return false;
        }
        _ept.allFriendshipRate =
        _ept.friendshipRate + 0.1f * _ept.friendshipRate * bonus;
        _ept.allNonSpecialtyRate =
        (float)(_ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate * bonus);
        return true;
    }
    
    public void pullEvalV2(float allFriendshipRate)
    {
        evalV2();
        allFriendshipRate = _ept.allFriendshipRate;
    }
    
    public boolean evalV3()
    {
        evalV2();
        _ept.specialtyRate =
        ((_rate.specialtyPriority + _unique.specialtyPriority) + 100.0f) /
        ((_rate.specialtyPriority + _unique.specialtyPriority) + 550.0f);
        _ept.unspecialtyRate =
        100.0f / ((_rate.specialtyPriority + _unique.specialtyPriority) + 550.0f);
        _ept.nonTrainingRate = 1.0f - _ept.specialtyRate - 4.0f * _ept.unspecialtyRate;
        _ept.v3 =
        (int)(((_ept.specialtyRate * _ept.allFriendshipRate + (_ept.unspecialtyRate * _ept.allNonSpecialtyRate) * 4) - 0.909f) * 1000);
        return true;
    }
    
    public void pullEvalV3(float specialtyRate, float unspecialtyRate, float nonTrainingRate)
    {
        evalV3();
        specialtyRate = _ept.specialtyRate;
        unspecialtyRate = _ept.unspecialtyRate;
        nonTrainingRate = _ept.nonTrainingRate;
    }

    public boolean evalV4()
    {
        evalV2();
        //失效率
        _ept.failureRate =
        ((80.0f - (_rate.initalFriendship + _unique.initalFriendship)) / 5.0f) / 72.0f;
        //失效友情训练率(failure_strike_rate)
        float failureSpecialtyRate = _ept.failureRate * _ept.specialtyRate;
        //有效友情训练率(success_strike_rate)
        float successSpecialtyRate = 1.0f - failureSpecialtyRate;
        //失效逛街训练率(failure_unstrike_rate)
        float failureUnspecialtyRate = _ept.failureRate * _ept.unspecialtyRate;
        //有效逛街训练率(success_unstrike_rate)
        float successUnspecialtyRate = 1.0f - failureUnspecialtyRate;
        
        _ept.nonSpecialtySpRate = _ept.nonSpecialtyRate + 0.1f * _ept.nonSpecialtyRate * _bonus.sp;
        _ept.specialtySpRate = _ept.friendshipRate + 0.1f * _ept.friendshipRate * _bonus.sp;
        
        _ept.v4main =
        (int)(((_ept.allFriendshipRate * successSpecialtyRate) +
        (_ept.allNonSpecialtyRate * failureSpecialtyRate) - (successSpecialtyRate + failureSpecialtyRate)) * 1000);
        _ept.v4sp =
        (int)(((failureSpecialtyRate + failureUnspecialtyRate) * _ept.nonSpecialtySpRate +
        (successSpecialtyRate + successUnspecialtyRate) * _ept.specialtySpRate -
        ((failureSpecialtyRate + failureUnspecialtyRate) + (successSpecialtyRate + successUnspecialtyRate))) * 1000);
        switch (_basic.type)
        {
            case 0:
                _ept.v4fold =
                    (int)((_ept.unspecialtyRate * (4 * _ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate *
                        (_bonus.stamina + _bonus.power + _bonus.guts + _bonus.wit)) - 4 * _ept.unspecialtyRate) * 1000);
                    break;
            case 1:
                _ept.v4fold =
                    (int)((_ept.unspecialtyRate * (4 * _ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate *
                        (_bonus.speed + _bonus.power + _bonus.guts + _bonus.wit)) - 4 * _ept.unspecialtyRate) * 1000);
                break;
            case 2:
                _ept.v4fold =
                    (int)((_ept.unspecialtyRate * (4 * _ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate *
                        (_bonus.stamina + _bonus.speed + _bonus.guts + _bonus.wit)) - 4 * _ept.unspecialtyRate) * 1000);
                break;
            case 3:
                _ept.v4fold =
                    (int)((_ept.unspecialtyRate * (4 * _ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate *
                        (_bonus.stamina + _bonus.power + _bonus.speed + _bonus.wit)) - 4 * _ept.unspecialtyRate) * 1000);
                break;
            case 4:
                _ept.v4fold =
                    (int)((_ept.unspecialtyRate * (4 * _ept.nonSpecialtyRate + 0.1 * _ept.nonSpecialtyRate *
                        (_bonus.stamina + _bonus.power + _bonus.guts + _bonus.speed)) - 4 * _ept.unspecialtyRate) * 1000);
                break;
            default:
                _ept.v4fold = (int)-1.0f;
                return false;
            }
        return true;
    }
                        
    public void pullEvalV4(int v4main, int v4fold, int v4sp)
    {
        evalV4();
        v4main = _ept.v4main;
        v4fold = _ept.v4fold;
        v4sp = _ept.v4sp;
    }
    
    public void pushRate(String attr, int carrier)
    {
        if (attr == "friendshipBonus")
            {
            carrier = _rate.friendshipBonus;
        }
        else if (attr == "moodEffect")
            {
            carrier = _rate.moodEffect;
        }
        else if (attr == "traningEffect")
            {
            carrier = _rate.traningEffect;
        }
        else if (attr == "initalFriendship")
            {
            carrier = _rate.initalFriendship;
        }
        else if (attr == "specialtyPriority")
            {
            carrier = _rate.specialtyPriority;
        }
    }
    
    public int pushRate(String attr)
    {
        if (attr == "friendshipBonus")
            {
            return _rate.friendshipBonus;
        }
        else if (attr == "moodEffect")
            {
            return _rate.moodEffect;
        }
        else if (attr == "traningEffect")
            {
            return _rate.traningEffect;
        }
        else if (attr == "initalFriendship")
            {
            return _rate.initalFriendship;
        }
        else if (attr == "specialtyPriority")
            {
            return _rate.specialtyPriority;
        }
        return 0;
    }
    
    public void pushUnique(String attr, int carrier)
    {
        if (attr == "friendshipBonus")
            {
            carrier = _unique.friendshipBonus;
        }
        else if (attr == "moodEffect")
            {
            carrier = _unique.moodEffect;
        }
        else if (attr == "traningEffect")
            {
            carrier = _unique.traningEffect;
        }
        else if (attr == "initalFriendship")
            {
            carrier = _unique.initalFriendship;
        }
        else if (attr == "specialtyPriority")
            {
            carrier = _unique.specialtyPriority;
        }
    }
    
    public int pushUnique(String attr)
    {
        if (attr == "friendshipBonus")
            {
            return _unique.friendshipBonus;
        }
        else if (attr == "moodEffect")
            {
            return _unique.moodEffect;
        }
        else if (attr == "traningEffect")
            {
            return _unique.traningEffect;
        }
        else if (attr == "initalFriendship")
            {
            return _unique.initalFriendship;
        }
        else if (attr == "specialtyPriority")
            {
            return _unique.specialtyPriority;
        }
        return 0;
    }
    
    public void pushBonus(String attr, int carrier)
    {
        if (attr == "speed")
            {
            carrier = _bonus.speed;
        }
        else if (attr == "stamina")
            {
            carrier = _bonus.stamina;
        }
        else if (attr == "power")
            {
            carrier = _bonus.power;
        }
        else if (attr == "guts")
            {
            carrier = _bonus.guts;
        }
        else if (attr == "wit")
            {
            carrier = _bonus.wit;
        }
        else if (attr == "sp")
            {
            carrier = _bonus.sp;
        }
    }
    
    public int pushBonus(String attr)
    {
        if (attr == "speed")
            {
            return _bonus.speed;
        }
        else if (attr == "stamina")
            {
            return _bonus.stamina;
        }
        else if (attr == "power")
            {
            return _bonus.power;
        }
        else if (attr == "guts")
            {
            return _bonus.guts;
        }
        else if (attr == "wit")
            {
            return _bonus.wit;
        }
        else if (attr == "sp")
            {
            return _bonus.sp;
        }
        return 0;
    }
    
    public void pushEpt(String attr, float carrier)
    {
        if (attr == "friendshipRate")
            {
            carrier = _ept.friendshipRate;
        }
        else if (attr == "nonSpecialtyRate")
            {
            carrier = _ept.nonSpecialtyRate;
        }
        else if (attr == "allFriendshipRate")
            {
            carrier = _ept.allFriendshipRate;
        }
        else if (attr == "allNonSpecialtyRate")
            {
            carrier = _ept.allNonSpecialtyRate;
        }
        else if (attr == "nonSpecialtySpRate")
            {
            carrier = _ept.nonSpecialtySpRate;
        }
        else if (attr == "specialtySpRate")
            {
            carrier = _ept.specialtySpRate;
        }
        else if (attr == "specialtyRate")
            {
            carrier = _ept.specialtyRate;
        }
        else if (attr == "unspecialtyRate")
            {
            carrier = _ept.unspecialtyRate;
        }
        else if (attr == "nonTrainingRate")
            {
            carrier = _ept.nonTrainingRate;
        }
        else if (attr == "failureRate")
            {
            carrier = _ept.failureRate;
        }
    }
    
    public void pushEpt(String attr, int carrier)
    {
        if (attr == "v3")
            {
            carrier = (_ept.v3);
        }
        else if (attr == "v4main")
            {
            carrier = (_ept.v4main);
        }
        else if (attr == "v4fold")
            {
            carrier = (_ept.v4fold);
        }
        else if (attr == "v4sp")
            {
            carrier = (_ept.v4sp);
        }
    }
    
    public float pushEpt(String attr)
    {
        if (attr == "friendshipRate")
            {
            return _ept.friendshipRate;
        }
        else if (attr == "nonSpecialtyRate")
            {
            return _ept.nonSpecialtyRate;
        }
        else if (attr == "allFriendshipRate")
            {
            return _ept.allFriendshipRate;
        }
        else if (attr == "allNonSpecialtyRate")
            {
            return _ept.allNonSpecialtyRate;
        }
        else if (attr == "nonSpecialtySpRate")
            {
            return _ept.nonSpecialtySpRate;
        }
        else if (attr == "specialtySpRate")
            {
            return _ept.specialtySpRate;
        }
        else if (attr == "specialtyRate")
            {
            return _ept.specialtyRate;
        }
        else if (attr == "unspecialtyRate")
            {
            return _ept.unspecialtyRate;
        }
        else if (attr == "nonTrainingRate")
            {
            return _ept.nonTrainingRate;
        }
        else if (attr == "failureRate")
            {
            return _ept.failureRate;
        }
        return 0.0f;
    }
    
    public int pushIntEpt(String attr)
    {
        if (attr == "v3")
            {
            return (_ept.v3);
        }
        else if (attr == "v4main")
            {
            return (_ept.v4main);
        }
        else if (attr == "v4fold")
            {
            return (_ept.v4fold);
        }
        else if (attr == "v4sp")
            {
            return (_ept.v4sp);
        }
        return 0;
    }
    
    public void getRate(String attr, int value) throws Exception
    {
        if (attr == "friendshipBonus")
            {
            _rate.friendshipBonus = value;
        }
        else if (attr == "moodEffect")
            {
            _rate.moodEffect = value;
        }
        else if (attr == "traningEffect")
            {
            _rate.traningEffect = value;
        }
        else if (attr == "initalFriendship")
            {
            _rate.initalFriendship = value;
        }
        else if (attr == "specialtyPriority")
            {
            _rate.specialtyPriority = value;
        }
        else
            {
            throw(new Exception("未知的属性: " + attr));
        }
    }
    
    public void getUnique(String attr, int value) throws Exception
    {
        if (attr == "friendshipBonus")
            {
            _unique.friendshipBonus = value;
        }
        else if (attr == "moodEffect")
            {
            _unique.moodEffect = value;
        }
        else if (attr == "traningEffect")
            {
            _unique.traningEffect = value;
        }
        else if (attr == "initalFriendship")
            {
            _unique.initalFriendship = value;
        }
        else if (attr == "specialtyPriority")
            {
            _unique.specialtyPriority = value;
        }
        else
            {
            throw(new Exception("未知的属性: " + attr));
        }
    }
    
    public void getBonus(String attr, int value) throws Exception
    {
        if (attr == "speed")
            {
            _bonus.speed = value;
        }
        else if (attr == "stamina")
            {
            _bonus.stamina = value;
        }
        else if (attr == "power")
            {
            _bonus.power = value;
        }
        else if (attr == "guts")
            {
            _bonus.guts = value;
        }
        else if (attr == "wit")
            {
            _bonus.wit = value;
        }
        else if (attr == "sp")
            {
            _bonus.sp = value;
        }
        else
            {
            throw(new Exception("未知的属性: " + attr));
        }
    }
}
