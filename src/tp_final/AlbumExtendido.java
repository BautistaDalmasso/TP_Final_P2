package tp_final;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AlbumExtendido extends Album {
	private static String PREMIO_EXTENDIDO = "Una pelota y un viaje";
	
	private Map<String, ArrayList<FiguritaTOP10>> seccionMejoresJugadores;
	
	
	public AlbumExtendido(String[] paisesClasificados, String[] listadoDeMundiales) {
		super(paisesClasificados, PREMIO_EXTENDIDO);
		
		generarSeccionMejoresJugadores(listadoDeMundiales);
	}

	public boolean figuritaEstaPegada(int codigoFigurita) {
	
	}
	
	public Lista<Figurita> pegarFiguritas(Lista<Figurita> figuritas) {
	
	}
	
	public boolean verificarAlbumCompleto() {
	
	}
	
	private void generarSeccionMejoresJugadores(String[] listadoDeMundiales) {
		seccionMejoresJugadores = new HashMap<String, ArrayList<FiguritaTOP10>>();
		
		for(String mundial : listadoDeMundiales) {
			seccionMejoresJugadores.put(mundial, new ArrayList<FiguritaTOP10>());
		}
	}
}
