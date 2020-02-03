package corseproject.domain;

public enum Position {
    FINANCE, BOSS, EMPLOYEE;
    static public Position getPosFromString(String pos){
        if(pos.equals("finance")) {
            return FINANCE;
        }else if (pos.equals("boss")){
            return BOSS;
        }else {
            return EMPLOYEE;
        }

    }

}
