package tp_final;

import java.util.LinkedList;
import java.util.List;

public class Participante {
	private int dni;
	private String nombreDeUsuario;
	private List<Figurita> coleccionDeFiguritas;
	private List<Figurita> figuritasRepetidas;
	private Album album;

	
	public Participante(int dni, String nombreDeUsuario, Album album) {
		this.coleccionDeFiguritas = new LinkedList<Figurita>();
		this.figuritasRepetidas = new LinkedList<Figurita>();
		
		this.dni = dni;
		this.nombreDeUsuario = nombreDeUsuario;
		this.album = album;
	}

	public void agregarFiguritasAColeccion(List<Figurita> figuritas) {
		for (Figurita f : figuritas) {
			if (coleccionDeFiguritas.contains(f) ||
					album.figuritaEstaPegada(f)) {
				figuritasRepetidas.add(f);
			} else {
				coleccionDeFiguritas.add(f);
			}
		}
	}
	
	public List<String> pegarFiguritas() {
		List<String> resultado = album.pegarFiguritas(coleccionDeFiguritas);
		
		// Vacia las figuritas una vez que fueron pegadas.
		coleccionDeFiguritas = new LinkedList<Figurita>();
		
		return resultado;
	}
	
	public boolean verificarAlbumCompleto() {
		return album.verificarAlbumCompleto();
	}
	
	public boolean verificarArgentinaCompleto() {
		return album.verificarArgentinaCompleto();
	}
	
	public Figurita buscarFiguritaIntercambiable(double valorFigurita) {
		/* Trata de devolver una figurita repetida de mayor valor posible,
		manteniendose en un valor <= a valorFigurita. */
		Figurita resultado = null;
		
		for (Figurita f : figuritasRepetidas) {
			if (f.calcularPrecio() <= valorFigurita) {
				if (resultado == null ||
						resultado.calcularPrecio() < f.calcularPrecio()) 
				{
					resultado = f;
				}
			}
		}
		
		return resultado;
	}
	
	public int verCodigoAlbum() {
		return album.getCodigoUnico();
	}

	public int verCodigoPromocional() {
		AlbumWeb album = (AlbumWeb) this.album;
		return album.verCodigoPromocional();
	}

	public int verNumeroParaSorteo() {
		AlbumTradicional album = (AlbumTradicional) this.album;
		return album.verNumeroParaSorteo();
	}
	
	public String toString() {
		return "(" + dni + ") " + nombreDeUsuario;
	}
	
	public String toStringInformativo() {
		String tipoDeAlbum =
				album instanceof AlbumTradicional ? "Tradicional" :
					(album instanceof AlbumWeb ? "Web" : "Extendido");
		return toString() + ": " + tipoDeAlbum;
	}
	
	public String ganadorString() {
		// String utilizado para lista de participantes ganadores.
		return " - " + toString() + ": " + album.getPremio();
	}

	public boolean verificarPaisCompleto(String nombrePais) {
		return album.verificarPaisCompleto(nombrePais);
	}

	public String verNombre() {
		return nombreDeUsuario;
	}

	public String verPremio() {
		return album.getPremio();
	}

	public int buscarFiguritaRepetida() {
		if (figuritasRepetidas.isEmpty()) {
			return -1;
		}
		return figuritasRepetidas.get(0).getNumeroIdentificador();
	}

	public Album getAlbum() {
		return album;
	}
}
