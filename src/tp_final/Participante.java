package tp_final;

import java.util.LinkedList;
import java.util.List;

public class Participante {
	private int dni;
	private String nombreDeUsuario;
	private List<Figurita> coleccionDeFiguritas;
	private List<Figurita> figuritasRepetidas;
	private String tipoDeAlbum;
	private Album album;

	
	public Participante(int dni, String nombreDeUsuario, String tipoDeAlbum, Album album) {
		this.coleccionDeFiguritas = new LinkedList<Figurita>();
		this.figuritasRepetidas = new LinkedList<Figurita>();
		
		this.dni = dni;
		this.nombreDeUsuario = nombreDeUsuario;
		this.tipoDeAlbum = tipoDeAlbum;
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
	
	}
	
	public int verCodigoAlbum() {
		return album.getCodigoUnico();
	}

	public String verTipoDeAlbum() {
		return tipoDeAlbum;
	}

	public int verCodigoPromocional() {
		if (!tipoDeAlbum.equals("Web")) {
			throw new RuntimeException("Solo albumes web tienen codigo promocional.");
		}
		AlbumWeb album = (AlbumWeb) this.album;
		return album.verCodigoPromocional();
	}

	public int verNumeroParaSorteo() {
		if (!tipoDeAlbum.equals("Tradicional")) {
			throw new RuntimeException("Necesita un album tradicional.");
		}
		
		AlbumTradicional album = (AlbumTradicional) this.album;
		
		return album.verNumeroParaSorteo();
	}
	
	public String toString() {
		return "(" + dni + ") " + nombreDeUsuario;
	}
	
	public String toStringInformativo() {
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
}
