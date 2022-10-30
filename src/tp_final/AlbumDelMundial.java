package tp_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlbumDelMundial implements IAlbumDelMundial {
	private Map<Integer, Participante> coleccionistasParticipantes;
	private List<Participante> listaParticipantes;
	private Fabrica fabrica;
	
	private List<Integer> codigosPromocionalesRedimidos;
	private List<Integer> sorteosRedimidos;
	
	public AlbumDelMundial() {
		coleccionistasParticipantes = new HashMap<Integer, Participante>();
		listaParticipantes = new LinkedList<Participante>();
		fabrica = new Fabrica();
		
		codigosPromocionalesRedimidos = new LinkedList<Integer>();
		sorteosRedimidos = new LinkedList<Integer>();
	}

	public int registrarParticipante(int dni, String nombre, String tipoDeAlbum) {
		if(coleccionistasParticipantes.containsKey(dni)) {
			throw new RuntimeException("Este dni ya esta registrado.");
		}
		
		Album albumSeleccionado = generarAlbum(tipoDeAlbum);
		
		Participante nuevoParticipante = new Participante(dni, nombre, tipoDeAlbum, albumSeleccionado);
		coleccionistasParticipantes.put(dni, nuevoParticipante);
		listaParticipantes.add(nuevoParticipante);
		
		
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

	public List<String> pegarFiguritas(int dni) {
		asegurarRegistro(dni);
		
		Participante p = coleccionistasParticipantes.get(dni);
		
		return p.pegarFiguritas();
	}
	
	public boolean llenoAlbum(int dni) {
		asegurarRegistro(dni);
		
		Participante p = coleccionistasParticipantes.get(dni);
		
		return p.verificarAlbumCompleto();
	}
	
	public String aplicarSorteoInstantaneo(int dni) {
		asegurarRegistro(dni);
		Participante comprador = coleccionistasParticipantes.get(dni);
		
		if (!comprador.verTipoDeAlbum().equals("Tradicional")) {
			throw new RuntimeException("Necesita un album tradicional.");
		}
		int numSorteo = comprador.verNumeroParaSorteo();
		if (sorteosRedimidos.contains(numSorteo)) {
			throw new RuntimeException("Numero ya redimido.");
		}
		
		sorteosRedimidos.add(numSorteo);
		return fabrica.sortear();
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
		StringBuilder resultado = new StringBuilder();
		
		for (Participante p : listaParticipantes) {
			if (p.verificarAlbumCompleto()) {
				resultado.append(p.ganadorString());
			}
		}
		
		return resultado.toString();
	}
	
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		LinkedList<String> resultado = new LinkedList<String>();
		
		for (Participante p: listaParticipantes) {
			if (p.verificarPaisCompleto(nombrePais)) {
				resultado.add(p.toStringInformativo());
			}
		}
		
		return resultado;
	}
	
	public List<String> verParticipantesConArgentinaCompletado() {
		return participantesQueCompletaronElPais("Argentina");
	}
	
	private void asegurarRegistro(int dni) {
		// Provoca un error si el dni no esta registrado en participantes.
		if (!coleccionistasParticipantes.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado.");
		}
	}
}
