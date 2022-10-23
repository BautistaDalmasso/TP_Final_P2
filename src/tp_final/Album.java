package tp_final;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Album {
	private static int cantidadDeAlbumes;
	
	private int codigoUnico;
	private Map<String, ArrayList<Figurita>> seccionPaisesParticipantes;
	private String premios;
	
	
	public Album(String[] paisesClasificados, String premios) {
		// El codigo unico es el numero de album.
		codigoUnico = Album.cantidadDeAlbumes;
		Album.aumentarCantidadDeAlbumes();
		
		generarSeccionPaises(paisesClasificados);
		
		this.premios = premios;
	}

	
	public boolean figuritaEstaPegada(int codigoFigurita) {
	
	}
	
	public Lista<Figurita> pegarFiguritas(Lista<Figurita> figuritas) {
	
	}
	
	public boolean verificarAlbumCompleto() {
	
	}
	
	public boolean verificarArgentinaCompleto() {
	
	}
	
	public int getCodigoUnico() {
		return codigoUnico;
	}
	
	
	private void generarSeccionPaises(String[] paisesClasificados) {
		seccionPaisesParticipantes = new HashMap<String, ArrayList<Figurita>>();
		
		for (String nombrePais : paisesClasificados) {
			seccionPaisesParticipantes.put(nombrePais, new ArrayList<Figurita>());
		}
	}
	
	private static void aumentarCantidadDeAlbumes() {
		Album.cantidadDeAlbumes++;
	}
}
