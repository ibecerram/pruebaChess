import java.util.*;
public class Movimientos 
{
	//Color piezaBlanca = Color.WHITE;
    //Color piezaNegra = Color.BLACK;

	private Piece pieza;
	private int coordenadaX;
	private int coordenadaY;
	private Coordinate coordinate;
	private List<Coordinate> listaMovimientosFiltrados = new ArrayList<>();
	private Board board;

	private List<Coordinate> listaMovimientosOponente = new ArrayList<>();
	boolean flagOponente = false;
	private List<Coordinate> listaMovimientosJugadors = new ArrayList<>();
	
	boolean flagJugador = false;

	public Movimientos(Piece pieza, Board board)
	{
		this.pieza = pieza;
		this.coordinate = pieza.getPosition();
		this.coordenadaX = this.coordinate.getX();
		this.coordenadaY = this.coordinate.getY();
		this.board = board;
	}

	public List<Coordinate> obtenerMovimientos()
	{
		String nombrePieza = pieza.getPiece_Type();
		System.out.println(nombrePieza);
		System.out.println(coordenadaX);
		System.out.println(coordenadaY);
		System.out.println(pieza.getColor());

		if(pieza.getColor().equals(Color.BLACK))
		{
			System.out.println("JUGADOR es NEGRA");
			System.out.println("OPONENTES color BLANCO");
			this.setMovimientosJugador(board, Color.BLACK);
			this.setMovimientosOponente(board, Color.WHITE);
		}
		else
		{
			System.out.println("JUGADOR BLANCO");
			System.out.println("OPONENTE NEGRO");
			this.setMovimientosJugador(board, Color.WHITE);
			this.setMovimientosOponente(board, Color.BLACK);
		}

		switch(nombrePieza)
		{
			case "King": //Rey
			{
				this.movimientosRey();
				break;
			}

			case "Queen": //Reina
			{
				this.movimientosReina();
				break;
			}

			case "Bishop": //Alfil
			{
				this.movimientosAlfil();
				break;
			}

			case "Knight": //Caballo
			{
				this.movimientosCaballo();
				break;
			}

			case "Rook": //Torre
			{
				this.movimientosTorre();
				break;
			}

			case "Pawn": //Peon
			{
				//Algún If Color 
				if(pieza.getColor().equals(Color.BLACK))
				{
					System.out.println("Peon Color Negro");
					this.movimientoPeonNegro();
				}
				else
				{
					System.out.println("Peon Color BLANCO");
					this.movimientoPeonBlanco();
				}
				break;
			}
		}

		return this.listaMovimientosFiltrados;
	}

	//REINA
	public void movimientosReina()
	{

		this.movimientoDiagonalIzquierdaS();
		this.movimientoDiagonalIzquierdaI();
		this.movimientoDiagonalDerechaS();
		this.movimientoDiagonalDerechaI();
		this.movimientoSimpleSuperior();
		this.movimientoSimpleInferior();
		this.movimientoLateralDerecho();
		this.movimientoLateralIzquierdo();
		this.movimientoVerticalSuperior();
		this.movimientoVerticalInferior();
	}

	public void movimientosRey()
	{
		this.movimientoCircularIzquierdo();
		this.movimientoCircularDerecho();
		this.movimientoCircularSuperior();
		this.movimientoCircularInferior();
	}

	public void movimientosAlfil()
	{
		this.movimientoDiagonalIzquierdaS();
		this.movimientoDiagonalIzquierdaI();
		this.movimientoDiagonalDerechaS();
		this.movimientoDiagonalDerechaI();
	}

	public void movimientosCaballo()
	{
		this.movimientoLIzquierdaSuperior();
		this.movimientoLIzquierdaInferior();
		this.movimientoLDerechaSuperior();
		this.movimientoLDerechaInferior();
	}

	public void movimientosTorre()
	{
		this.movimientoLateralDerecho();
		this.movimientoLateralIzquierdo();
		this.movimientoVerticalSuperior();
		this.movimientoVerticalInferior();
	}

	public void movimientoDiagonalIzquierdaS()
	{
		int columnas = this.coordenadaY - 1;

		for(int filas = this.coordenadaX-1; filas >= 0; filas--, columnas--)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoDiagonalIzquierdaI()
	{
		int columnas = this.coordenadaY - 1;
		for(int filas = this.coordenadaX+1; filas <= 7; filas++, columnas--)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}

			}
		}
	}

	public void movimientoDiagonalDerechaS()
	{
		int columnas = this.coordenadaY + 1;
		for(int filas = this.coordenadaX-1; filas >= 0; filas--, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
				{
					this.listaMovimientosFiltrados.add(coordenada);
					
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoDiagonalDerechaI()
	{
		//Diagonal Derecha Inferior
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY + 1;
		for(int filas = this.coordenadaX+1; filas <= 7; filas++, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoSimpleInferior()
	{
		int filas = this.coordenadaX;
		int columnas = this.coordenadaY - 1;
		for(int pasos = 0; pasos < 2; pasos++, columnas = columnas+2)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoSimpleSuperior()
	{
		int filas = this.coordenadaX - 1;
		int columnas = this.coordenadaY;
		for(int pasos = 0; pasos < 2; pasos++, filas = filas+2)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoVerticalSuperior()
	{
		int columnas = this.coordenadaY;
		for(int filasF = this.coordenadaX - 1; filasF >= 0; filasF--)
		{
			if(this.verificarMovimientoLimitesTablero(filasF, columnas))
			{
				Coordinate coordenada = new Coordinate(filasF, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoVerticalInferior()
	{
		int columnas = this.coordenadaY;
		for(int filasF = this.coordenadaX + 1; filasF <= 7; filasF++)
		{
			if(this.verificarMovimientoLimitesTablero(filasF, columnas))
			{
				Coordinate coordenada = new Coordinate(filasF, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLateralDerecho()
	{
		//Parte Izquierda
		//System.out.println("--------------------------");
		int filas = this.coordenadaX;
		for(int columnasC = this.coordenadaY - 1; columnasC >= 0; columnasC--)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnasC))
			{
				Coordinate coordenada = new Coordinate(filas, columnasC);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLateralIzquierdo()
	{
		//Parte Derecha
		//System.out.println("--------------------------");
		int filas = this.coordenadaX;
		for(int columnasC = this.coordenadaY + 1; columnasC <= 7; columnasC++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnasC))
			{
				Coordinate coordenada = new Coordinate(filas, columnasC);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
				
		}
	}

	public void movimientoCircularSuperior()
	{
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY - 1;
		int filas = this.coordenadaX - 1;
		//Parte Superior
		for(int pasos = 0; pasos < 3; pasos++, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoCircularIzquierdo()
	{
		int columnas = this.coordenadaY - 1;
		int filas = this.coordenadaX - 1;
		//Columna Izquierda
		for(int pasos = 0; pasos < 3; pasos++, filas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoCircularInferior()
	{
		int columnas = this.coordenadaY - 1;
		int filas = this.coordenadaX + 1;
		//Parte Inferior
		for(int pasos = 0; pasos < 3; pasos++, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoCircularDerecho()
	{
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY + 1;
		int filas = this.coordenadaX - 1;
		//Parte Columna Derecha
		for(int pasos = 0; pasos < 3; pasos++, filas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLIzquierdaSuperior()
	{
		//Movimientos Esquina Superior Izquiera
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY - 2;
		int filas = this.coordenadaX - 1;
		for(int pasos = 0; pasos < 2; pasos++, filas--, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLIzquierdaInferior()
	{
		//Movimientos Esquina Inferior Izquierda
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY - 2;
		int filas = this.coordenadaX + 1;
		for(int pasos = 0; pasos < 2; pasos++, filas++, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLDerechaSuperior()
	{
		//Movimientos Esquina Superior Derecha
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY + 1;
		int filas = this.coordenadaX - 2;
		for(int pasos = 0; pasos < 2; pasos++, filas++, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoLDerechaInferior()
	{
		//Movimientos Esquina Inferior Derecha
		//System.out.println("--------------------------");
		int columnas = this.coordenadaY + 1;
		int filas = this.coordenadaX + 2;
		for(int pasos = 0; pasos < 2; pasos++, filas--, columnas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public void movimientoPeonBlanco()
	{
		int filas = this.coordenadaX - 1;
		int columnas = this.coordenadaY;

		//System.out.println("--------------------------");
		for(int pasos = 0; pasos < 2; pasos++, filas--)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	//PEON NEGRO
	public void movimientoPeonNegro()
	{
		int filas = this.coordenadaX + 1;
		int columnas = this.coordenadaY;

		//System.out.println("--------------------------");
		for(int pasos = 0; pasos < 2; pasos++, filas++)
		{
			if(this.verificarMovimientoLimitesTablero(filas, columnas))
			{
				Coordinate coordenada = new Coordinate(filas, columnas);
				if(this.validarMovimientos(coordenada))
				{
					if(this.validarMovimientos(coordenada) && this.agregarMovimientoSinDuplicarse(coordenada))
					{
						this.listaMovimientosFiltrados.add(coordenada);
					}
				}
				else
				{
					break;
				}
			}
		}
	}

	public String obtenerCoordenadas(int filas, int columnas)
	{
		return String.valueOf(filas) + " " + String.valueOf(columnas);
	}

	public boolean verificarMovimientoLimitesTablero(int filas, int columnas)
	{
		if(filas < 0 || filas > 7 || columnas < 0 || columnas > 7)
		{
			return false;
		}
		
		return true;
	}

	/*public void obtenerMovimientosBoard(Board board)
	{
		System.out.println("Funciona");
	}*/
	public void setMovimientosOponente(Board board, Color color)
	{
		//System.out.println("----------------------PIEZAS OPONENTE");
		this.listaMovimientosOponente = new ArrayList<>(board.obtenerPiezasPorColor(color));
		/*
		System.out.println("PIEZAS NEGRAS");
		this.listaMovimientosOponente = new ArrayList<>(board.obtenerPiezasCoordenadas(color));
		for(int i = 0; i < this.listaMovimientosOponente.size(); i++)
		{
			System.out.println(this.listaMovimientosOponente.get(i));
		}*/
		
	}

	/*public void setMovimientosJugador(List<String> listaMovimientosJugadors)
	{
		System.out.println("PIEZAS BLANCAS");
		this.listaMovimientosJugadors = listaMovimientosJugadors;
		for(int i = 0; i < this.listaMovimientosJugadors.size(); i++)
		{
			System.out.println(this.listaMovimientosJugadors.get(i));
		}
		System.out.println("FIN SET");
	}*/

	public void setMovimientosJugador(Board board, Color color)
	{
		//System.out.println("----------------------PIEZAS JUGADOR");
		this.listaMovimientosJugadors = new ArrayList<>(board.obtenerPiezasPorColor(color));
		/*
		System.out.println("PIEZAS NEGRAS");
		this.listaMovimientosOponente = new ArrayList<>(board.obtenerPiezasCoordenadas(color));
		for(int i = 0; i < this.listaMovimientosOponente.size(); i++)
		{
			System.out.println(this.listaMovimientosOponente.get(i));
		}*/
		/*for(Coordinate coordinates : listaMovimientosJugadors)
		{
			System.out.println(coordinates.toString());
		}*/
	}

	public boolean validarMovimientoConOponente(Coordinate coordenadasXY)
	{
		//System.out.println("Verificando OPONENTE en: " + coordenadasXY.toString());
		for(Coordinate coordinates : this.listaMovimientosOponente)
		{
			if(coordinates.equals(coordenadasXY))
			{
				//System.out.println("Contiene");
				return true;
			}

		}
		//System.out.println("No contiene");
		return false;
	}

	public boolean validarMovimientoConJugador(Coordinate coordenadasXY)
	{
		for(Coordinate coordinates : this.listaMovimientosJugadors)
		{
			if(coordinates.equals(coordenadasXY))
			{
				//System.out.println("Contiene");
				return true;
			}
		}
		return false;
	}

	public boolean validarMovimientos(Coordinate coordenadasXY)
	{
		flagOponente = this.validarMovimientoConOponente(coordenadasXY);
		flagJugador = this.validarMovimientoConJugador(coordenadasXY);

		if(flagJugador)
		{
			return false;
		}
		
		//System.out.println("Esta aQui EN = " + coordenadasXY);
		//System.out.println(this.obtenerCoordenadas(filas, columnas));
		if(flagOponente)
		{
			this.listaMovimientosFiltrados.add(coordenadasXY);
			return false;
		}
		//		System.out.println("XXXXXXXXXXX");

		return true;
	}

	public void mostrarMovimientosFiltrados()
	{
		//this.eliminarDuplicadosLista();
		System.out.println("-------------------MOVIMIENTOS POSIBLES");
		for(int i = 0; i < this.listaMovimientosFiltrados.size(); i++)
		{
			System.out.println(this.listaMovimientosFiltrados.get(i));
		}

		if(this.listaMovimientosFiltrados.isEmpty())
		{
			System.out.println("No hay movimientos para esta pieza. ");
		}
	}

	public boolean agregarMovimientoSinDuplicarse(Coordinate coordenadasXY)
	{
		//System.out.println("ELIMINARA sinDuplicados");
		for(Coordinate coordinates : this.listaMovimientosFiltrados)
		{
			if(coordinates.equals(coordenadasXY))
			{
				return false;
			}
		}
		return true;
		/*Set<Coordinate> hashSet = new HashSet<>(this.listaMovimientosFiltrados);
		this.listaMovimientosFiltrados.clear();
		this.listaMovimientosFiltrados.addAll(hashSet);
		//Collections.sort(listaMovimientosFiltrados);*/
		/*List<Coordinate> sinDuplicados = new ArrayList<>();

		HashMap<Integer, Coordinate> map = new HashMap<Integer, Coordinate>();

		int i = 0;
		for(Coordinate coordinate : listaMovimientosFiltrados)
		{
			i++;
			map.put(i, coordinate);
		}

		/*for(Coordinate coordinatesX : this.listaMovimientosFiltrados)
		{
			if(!sinDuplicados.equals(coordinatesX))
			{
				System.out.println("Entro aquiiii. ");
				sinDuplicados.add(coordinatesX);
			}
		}*/

		/*for(Coordinate coordinatesX : this.listaMovimientosFiltrados)
		{
			System.out.println("Entro aqui PRIMERO");
			if(!sinDuplicados.isEmpty())
			{
				for(Coordinate coordinatesY : sinDuplicados)
				{
					System.out.println("Entro aqui SEGUNDO");
					if(!coordinatesY.equals(coordinatesX))
					{
						sinDuplicados2.add(coordinatesX);
						System.out.println("No lo Contiene");
						//return true;
					}
				}
			}
			else
			{
				sinDuplicados.add(coordinatesX);
				sinDuplicados2.add(coordinatesX);
			}
			

			/*if(!sinDuplicados.contains(coordinatesX))
			{
				sinDuplicados.add(coordinatesX);
				System.out.println("No lo Contiene");
			}

		}*/

		/*this.listaMovimientosFiltrados.clear();
		this.listaMovimientosFiltrados.addAll(sinDuplicados2);*/
	}
}