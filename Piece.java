/*Caracteres con figuritas *.*
DISPLAY_LOOKUP = {
    "R": '♜',
    "N": '♞',
    "B": '♝',
    "K": '♚',	
    "Q": '♛',
    "P": '♟',
    "p": '▲',
}
*/
enum Color{
    WHITE, 
    BLACK};
enum Alive{LIVE, DEAD};

public abstract class Piece extends Coordinate{
    private char figure; //Ver DISPLAY_LOOKUP
    private String piece_type;//Reina, caballito, peon ...
    private Coordinate position;
    private Color color;
    private Alive alive;

    //Lo deje por compatibilidad con las demas piezas
    public Piece(Color color, Coordinate position){
        this.color = color;
        this.alive = Alive.LIVE;
        this.position = position;
    }

    //Clase mas abstracta, maneja la figura y el nombre de la pieza
    public Piece(char figure, String piece, Color color, Coordinate position){
        this.figure = figure;
        this.piece_type = piece;
        this.color = color;
        this.alive = Alive.LIVE;
        this.position = position;
    }

    public void setPosition(Coordinate other){
        position.setPosition(other);
    }

    public Coordinate getPosition(){
        return position;
    }

    //Añado el get color para cuestiones de implementación de color de figuras en posiciones (Creo que se usará)
    public Color getColor(){
        return this.color;
    }

    public Alive isAlive(){
        return this.alive;
    }

    public void setFigure(char f){
        this.figure = f;
    }

    public void setFigureToLetter(){
        this.figure = piece_type.charAt(0);
    }

    public char getFigure(){
        return this.figure;
    }

    public String getPiece_Type(){
        return this.piece_type;
    }

    //Imprime información de la pieza
    //Ya se que se ve horrible
    public String toString(){
        return  "Name: " + this.getPiece_Type() + '\n' + 
                "Figure: " + String.valueOf(this.getFigure()) + '\n' + 
                "Color: " + (this.color == Color.WHITE ? "WHITE" : "BLACK") + '\n' +
                "Live: " + (this.alive == Alive.LIVE ? "LIVE" : "DEAD") + '\n' +
                "Position: " + this.position.toString() + '\n';
    }
    public void setFigureToFigure(){}
}
