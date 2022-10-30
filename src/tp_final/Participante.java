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
	
	}
	
	public List<String> pegarFiguritas() {
	
	}
	
	public boolean verificarAlbumCompleto() {
	
	}
	
	public boolean verificarArgentinaCompleto() {
	
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
}
