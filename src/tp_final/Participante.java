package tp_final;

import java.util.LinkedList;
import java.util.List;

public class Participante {
	private int dni;
	private String nombreDeUsuario;
	private List<FiguritaTradicional> coleccionDeFiguritas;
	private List<FiguritaTradicional> figuritasRepetidas;
	private Album album;

	
	public Participante(int dni, String nombreDeUsuario, Album album) {
		this.coleccionDeFiguritas = new LinkedList<FiguritaTradicional>();
		this.figuritasRepetidas = new LinkedList<FiguritaTradicional>();
		
		this.dni = dni;
		this.nombreDeUsuario = nombreDeUsuario;
		this.album = album;
	}

	public void agregarFiguritasAColeccion(List<FiguritaTradicional> figuritas) {
		for (FiguritaTradicional f : figuritas) {
			agregarFigurita(f);
		}
	}
	
	public void agregarFigurita(FiguritaTradicional f) {
		if (figuritaRepetida(f)) {
			figuritasRepetidas.add(f);
		} else {
			coleccionDeFiguritas.add(f);
		}
	}
	
	public List<String> pegarFiguritas() {
		List<String> resultado = album.pegarFiguritas(coleccionDeFiguritas);
		
		// Vacia las figuritas una vez que fueron pegadas.
		coleccionDeFiguritas = new LinkedList<FiguritaTradicional>();
		
		return resultado;
	}
	
	public boolean verificarAlbumCompleto() {
		return album.verificarAlbumCompleto();
	}
	
	public boolean verificarArgentinaCompleto() {
		return album.verificarArgentinaCompleto();
	}
	
	public FiguritaTradicional devolverFiguritaIntercambiable(double valorFigurita) {
		/* Trata de devolver (popeando) una figurita repetida de mayor valor posible,
		manteniendose en un valor <= a valorFigurita. */
		FiguritaTradicional resultado = null;
		
		for (FiguritaTradicional f : figuritasRepetidas) {
			if (f.calcularPrecio() <= valorFigurita) {
				if (resultado == null ||
						resultado.calcularPrecio() < f.calcularPrecio()) 
				{
					resultado = f;
				}
			}
		}
		
		figuritasRepetidas.remove(resultado);
		return resultado;
	}
	
	public int buscarFiguritaRepetida() {
		// Devuelve el código identificador de una figurita repetida.
		if (figuritasRepetidas.isEmpty()) {
			return -1;
		}
		return figuritasRepetidas.get(0).getNumeroIdentificador();
	}

	public FiguritaTradicional devolverFiguritaRepetida() {
		// Popea una figurita repetida.
		if (figuritasRepetidas.isEmpty()) {
			return null;
		}
		FiguritaTradicional res = figuritasRepetidas.get(0);
		figuritasRepetidas.remove(0);
		return res;
	}
	
	public FiguritaTradicional devolverFiguritaRepetida(int codigoFigurita) {
		// Popea un figurita repetida con el código indicado.
		for (FiguritaTradicional f : figuritasRepetidas) {
			if (f.getNumeroIdentificador() == codigoFigurita) {
				FiguritaTradicional resultado = f;
				figuritasRepetidas.remove(f);
				return resultado;
			}
		}
		return null;
	}
	
	public boolean aceptaFigurita(FiguritaTradicional f) {
		// Devuelve true si el participante no tiene la figurita repetida 
		// para evitar que intercambie una repetida por otra también repetida.
		return !figuritaRepetida(f);
	}
	
	private boolean figuritaRepetida(FiguritaTradicional f) {
		return coleccionDeFiguritas.contains(f) ||
				album.figuritaEstaPegada(f);
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
	
	public Album getAlbum() {
		return album;
	}
}
