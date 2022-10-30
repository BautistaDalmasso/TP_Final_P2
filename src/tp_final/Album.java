package tp_final;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public abstract class Album {
	private static int cantidadDeAlbumes;
	
	private int codigoUnico;
	private Map<String, ArrayList<Figurita>> seccionPaisesParticipantes;
	private String[] paises;
	private String premios;
	
	
	public Album(String[] paisesClasificados, String premios) {
		// El codigo unico es el numero de album.
		codigoUnico = Album.cantidadDeAlbumes;
		Album.aumentarCantidadDeAlbumes();
		
		this.paises = paisesClasificados;
		generarSeccionPaises(paisesClasificados);
		
		this.premios = premios;
	}

	
	public boolean figuritaEstaPegada(Figurita figurita) {
		ArrayList<Figurita> pagina = 
				seccionPaisesParticipantes.get(figurita.getNombrePais());
		
		return pagina.contains(figurita);
	}
	
	public List<String> pegarFiguritas(List<Figurita> figuritas) {
		LinkedList<String> resultado = new LinkedList<String>();
		
		for (Figurita f: figuritas) {
			pegarFigurita(f);
			
			resultado.add(f.toString());
		}
		
		return resultado;
	}
	
	public void pegarFigurita(Figurita figurita) {
		if (figuritaEstaPegada(figurita)) {
			throw new RuntimeException("Tratando de pegar figurita repetida.");
		}
		
		String nombrePais = figurita.getNombrePais();
		ArrayList<Figurita> pagina = seccionPaisesParticipantes.get(nombrePais);
		
		pagina.add(figurita);
	}
	
	public boolean verificarAlbumCompleto() {
		boolean estaCompleto = true;
		
		for (String pais: paises) {
			estaCompleto = estaCompleto &&
					seccionPaisesParticipantes.get(pais).size() == 12;
		}
		
		return estaCompleto;
	}
	
	public boolean verificarArgentinaCompleto() {
		return seccionPaisesParticipantes.get("Argentina").size() == 12;
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


	public String getPremio() {
		return premios;
	}
}
