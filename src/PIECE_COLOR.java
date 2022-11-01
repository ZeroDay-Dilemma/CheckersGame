public enum PIECE_COLOR {
    RED, BLACK;
    public String toString(){
        if (this == RED){
            return "Red";
        }
        else{
            return "Black";
        }
    }
    public byte toByte(){
        if (this == RED){
            return 1;
        }
        else{
            return 2;
        }
    }
    public byte toKingByte(){
        if (this == RED){
            return 10;
        }
        else{
            return 20;
        }
    }
    public PIECE_COLOR opposite(){
        if (this == RED){
            return BLACK;
        }
        else{
            return RED;
        }
    }
}