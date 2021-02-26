import java.util.*;

public class TestBoard {
    public static void main(String[] args) {
    	//Piece colorBlanco = Piece.Color.WHITE;
    	//Color color = Color.WHITE;

        Board myboard = new Board(8,8);
        System.out.println(myboard.drawBoard());
        myboard.boardToLetters();
        //System.out.println(myboard.drawBoard());
       // myboard.boardToFigures();
       // System.out.println(myboard.drawBoard());

        System.out.println("------------------- Movimientos Posibles");
        //myboard.obtenerPiezasPorColor();

        Coordinate coordinate = new Coordinate(4,3);
        Piece pieza = myboard.obtenerPiezaCoordenadas(coordinate);
        Movimientos movimientos = new Movimientos(pieza, myboard);
        ArrayList<Coordinate> listaMovimientos = new ArrayList<>(movimientos.obtenerMovimientos());

        //System.out.println(piece.toString());
        for(Coordinate coordenadas : listaMovimientos)
        {
            System.out.println(coordenadas.toString());
        }

        if(listaMovimientos.isEmpty())
        {
            System.out.println("Esta vacia");
        }
    }
}
