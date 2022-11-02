package tp_final;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AlbumExtendido extends Album {
	private static String PREMIO_EXTENDIDO = "Una pelota y un viaje";
	
	private Map<String, ArrayList<FiguritaTOP10>> seccionMejoresJugadores;
	private String[] mundiales;
	
	public AlbumExtendido(String[] paisesClasificados, String[] listadoDeMundiales) {
		super(paisesClasificados, PREMIO_EXTENDIDO);
		
		this.mundiales = listadoDeMundiales;
		generarSeccionMejoresJugadores(listadoDeMundiales);
	}

	@Override
	public boolean figuritaEstaPegada(FiguritaTradicional figurita) {
		if (figurita instanceof FiguritaTOP10) {
			ArrayList<FiguritaTOP10> pagina =
					seccionMejoresJugadores.get(figurita.getNombrePais());
			return pagina.contains(figurita);
		}
		
		return super.figuritaEstaPegada(figurita);
	}
	
	@Override
	public List<String> pegarFiguritas(List<FiguritaTradicional> figuritas) {
		LinkedList<String> resultado = new LinkedList<String>();
		
		for (FiguritaTradicional f: figuritas) {			
			if (f instanceof FiguritaTOP10) {
				pegarFiguritaTop10(f);
				resultado.add(f.toString());
			}
			else {
				super.pegarFigurita(f);
				resultado.add(f.toString());
			}
		}
		
		return resultado;
	}
	
	private void pegarFiguritaTop10(FiguritaTradicional f) {
		ArrayList<FiguritaTOP10> pagina = 
				seccionMejoresJugadores.get(f.getNombrePais());
		
		pagina.add((FiguritaTOP10) f);
	}

	@Override
	public boolean verificarAlbumCompleto() {
		boolean mejoresJugadoresCompleto = true;
		
		for (String mundial: mundiales) {
			mejoresJugadoresCompleto = mejoresJugadoresCompleto &&
					seccionMejoresJugadores.get(mundial).size() == 2;
		}
		
		return mejoresJugadoresCompleto && super.verificarAlbumCompleto();
	}
	
	private void generarSeccionMejoresJugadores(String[] listadoDeMundiales) {
		seccionMejoresJugadores = new HashMap<String, ArrayList<FiguritaTOP10>>();
		
		for(String mundial : listadoDeMundiales) {
			seccionMejoresJugadores.put(mundial, new ArrayList<FiguritaTOP10>());
		}
	}
}
