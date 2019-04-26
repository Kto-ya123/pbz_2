package corseproject.domain;

public enum Sex {
    MALE, FEMALE;

    static public Sex getSexFromString(String sex){
        if(sex.equals("male")){
            return Sex.MALE;
        }else if(sex.equals("female")){
            return Sex.FEMALE;
        }else{
            return Sex.MALE;
        }
    }
}
