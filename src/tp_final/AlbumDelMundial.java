package tp_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlbumDelMundial implements IAlbumDelMundial {
	private Map<Integer, Participante> coleccionistasParticipantes;
	private Fabrica fabrica;
	
	public AlbumDelMundial() {
		coleccionistasParticipantes = new HashMap<Integer, Participante>();
		fabrica = new Fabrica();
	}

	public int registrarParticipante(int dni, String nombre, String tipoDeAlbum) {
		if(coleccionistasParticipantes.containsKey(dni)) {
			throw new RuntimeException("Este dni ya esta registrado.");
		}
		
		Album albumSeleccionado = generarAlbum(tipoDeAlbum);
		
		Participante nuevoParticipante = new Participante(dni, nombre, tipoDeAlbum, albumSeleccionado);
		coleccionistasParticipantes.put(dni, nuevoParticipante);
		
		return nuevoParticipante.verCodigoAlbum();
	}
	
	private Album generarAlbum(String tipoDeAlbum) {
		/* Crea un nuevo album del tipo correcto y lanza 
		un error si el tipo es incorrecto. */
		switch(tipoDeAlbum) {
			case "Tradicional":
				return fabrica.crearAlbumTradicional();
			case "Web":
				return fabrica.crearAlbumWeb();
			case "Extendido":
				return fabrica.crearAlbumExtendido();
			default:
				throw new RuntimeException("Album no valido.");
		}
	}
	
	public void comprarFiguritas(int dni) {
	
	}
	
	public void comprarFiguritasTop10(int dni) {
	
	}
	
	public void comprarFiguritasConCodigoPromocional(int dni) {
	
	}
	
	public List<String> pegarFiguritas(int dni) {
	
	}
	
	public boolean llenoAlbum(int dni) {
	
	}
	
	public String aplicarSorteoInstantaneo(int dni) {
	
	}
	
	public int buscarFiguritaRepetida(int dni) {
	
	}
	
	public boolean intercambiar(int dni, int codigoFigurita) {
	
	}
	
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
	
	}
	
	public String darNombre(int dni) {
	
	}
	
	public String darPremio(int dni) {
	
	}
	
	public String listadoDeGanadores() {
	
	}
	
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
	
	}
	
	public List<String> verParticipantesConArgentinaCompletado() {
	
	}
}
