package tp_final;

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
		
		Participante nuevoParticipante = new Participante(dni, nombre, albumSeleccionado);
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

		List<FiguritaTradicional> sobre = fabrica.generarSobre(4);
		comprador.agregarFiguritasAColeccion(sobre);
	}

	public void comprarFiguritasTop10(int dni) {
		asegurarRegistro(dni);

		Participante comprador = coleccionistasParticipantes.get(dni);
		
		if (!(comprador.getAlbum() instanceof AlbumExtendido)) {
			throw new RuntimeException("Comprador debe tener un album extendido.");
		}
		
		List<FiguritaTradicional> sobre = fabrica.generarSobreTop10(4);
		comprador.agregarFiguritasAColeccion(sobre);
	}
	
	public void comprarFiguritasConCodigoPromocional(int dni) {
		asegurarRegistro(dni);
		
		Participante comprador = coleccionistasParticipantes.get(dni);
		
		if (!(comprador.getAlbum() instanceof AlbumWeb)) {
			throw new RuntimeException("Comprador debe tener un album web.");
		}
		int codigoPromocional = comprador.verCodigoPromocional();
		if (codigosPromocionalesRedimidos.contains(codigoPromocional)) {
			throw new RuntimeException("Código ya redimido");
		}
		
		List<FiguritaTradicional> sobre = fabrica.generarSobre(4);
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
		
		if (!(comprador.getAlbum() instanceof AlbumTradicional)) {
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
		asegurarRegistro(dni);
		
		Participante p = coleccionistasParticipantes.get(dni);
		return p.buscarFiguritaRepetida();
	}
	
	public boolean intercambiar(int dni, int codigoFigurita) {
		asegurarRegistro(dni);
		Participante intercambiador = coleccionistasParticipantes.get(dni);
		
		FiguritaTradicional f = intercambiador.devolverFiguritaRepetida(codigoFigurita);
		if (f == null) {
			throw new RuntimeException("Figurita invalida para intercambio.");
		}
		
		return intercambiarUnaFiguritaRepetida(intercambiador, f);
	}
	
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		asegurarRegistro(dni);
		Participante intercambiador = coleccionistasParticipantes.get(dni);
		
		FiguritaTradicional figuIntercambiador = intercambiador.devolverFiguritaRepetida();
		
		return intercambiarUnaFiguritaRepetida(intercambiador,figuIntercambiador);
	}
	private boolean intercambiarUnaFiguritaRepetida(Participante intercambiador,
			FiguritaTradicional figuIntercambiador)
	{	
		Participante companieroDeIntercambio = buscarAlbumDelMismoTipo(intercambiador);
		
		// Si no hay ningun participante con el cual intercambiar devolvemos false.
		if (companieroDeIntercambio == null) {
			return false;
		}
		
		// El participante que pidió el intercambio no tenía figuritas para
		// intercambiar.
		if (figuIntercambiador == null) {
			return true;
		}
		
		FiguritaTradicional figuCompaniero = companieroDeIntercambio
				.devolverFiguritaIntercambiable(figuIntercambiador.calcularPrecio());
		
		// El participante que pidió el intercambio tenía figuritas pero el
		// otro no.
		if (figuCompaniero == null) {
			intercambiador.agregarFigurita(figuIntercambiador);
			
			return false;
		}
		// Ambos participantes aceptan la figurita sugerida por el otro.
		if (intercambiador.aceptaFigurita(figuCompaniero) 
				&& companieroDeIntercambio.aceptaFigurita(figuIntercambiador)) {
			intercambiador.agregarFigurita(figuCompaniero);
			companieroDeIntercambio.agregarFigurita(figuIntercambiador);
			return true;
		}
		// Alguno de los participantes no acepto la figurita.
		intercambiador.agregarFigurita(figuIntercambiador);
		companieroDeIntercambio.agregarFigurita(figuCompaniero);
		return false;
	}

	private Participante buscarAlbumDelMismoTipo(Participante otro) {
		// Busca un participante distinto de otro con el mismo tipo de Album.		
		for (Participante p : listaParticipantes) {
			if (p.getAlbum().getClass().equals(otro.getAlbum().getClass())
					&& (!p.equals(otro))) {
				return p;
			}
		}
		return null;
	}
	
	public String darNombre(int dni) {
		asegurarRegistro(dni);
		
		Participante p = coleccionistasParticipantes.get(dni);
		
		return p.verNombre();
	}
	
	public String darPremio(int dni) {
		asegurarRegistro(dni);
		
		Participante p = coleccionistasParticipantes.get(dni);
		
		if (!p.verificarAlbumCompleto()) {
			throw new RuntimeException("Album no esta completo.");
		}
		
		return p.verPremio();
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

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("AlbumDelMundial [Participantes: ");
		for (Participante p : listaParticipantes) {
			res.append(p.toString());
			res.append(";");
		}
		res.replace(res.length()-1,res.length(), "]");
		return res.toString();
	}
}
