package tp_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlbumDelMundial implements IAlbumDelMundial {
	private Map<Integer, Participante> coleccionistasParticipantes;
	private Fabrica fabrica;
	
	private List<Integer> codigosPromocionalesRedimidos;
	
	public AlbumDelMundial() {
		coleccionistasParticipantes = new HashMap<Integer, Participante>();
		fabrica = new Fabrica();
		
		codigosPromocionalesRedimidos = new LinkedList<Integer>();
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
		asegurarRegistro(dni);

		Participante comprador = coleccionistasParticipantes.get(dni);

		List<Figurita> sobre = fabrica.generarSobre(4);
		comprador.agregarFiguritasAColeccion(sobre);
	}

	public void comprarFiguritasTop10(int dni) {
		asegurarRegistro(dni);

		Participante comprador = coleccionistasParticipantes.get(dni);
		
		if (!comprador.verTipoDeAlbum().equals("Extendido")) {
			throw new RuntimeException("Comprador debe tener un album extendido.");
		}
		
		List<Figurita> sobre = fabrica.generarSobreTop10(4);
		comprador.agregarFiguritasAColeccion(sobre);
	}
	
	public void comprarFiguritasConCodigoPromocional(int dni) {
		asegurarRegistro(dni);
		
		Participante comprador = coleccionistasParticipantes.get(dni);
		
		if (!comprador.verTipoDeAlbum().equals("Web")) {
			throw new RuntimeException("Comprador debe tener un album web.");
		}
		int codigoPromocional = comprador.verCodigoPromocional();
		if (codigosPromocionalesRedimidos.contains(codigoPromocional)) {
			throw new RuntimeException("Código ya redimido");
		}
		
		List<Figurita> sobre = fabrica.generarSobre(4);
		comprador.agregarFiguritasAColeccion(sobre);
		
		codigosPromocionalesRedimidos.add(codigoPromocional);
	}
	
	private void asegurarRegistro(int dni) {
		// Provoca un error si el dni no esta registrado en participantes.
		if (!coleccionistasParticipantes.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado.");
		}
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
