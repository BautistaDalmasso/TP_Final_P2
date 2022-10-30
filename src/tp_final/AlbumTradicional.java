package tp_final;

public class AlbumTradicional extends Album {
	private static String PREMIO_TRADICIONAL = "Una pelota";
	
	private int numeroParaSorteo;
	
	public AlbumTradicional(String[] paisesClasificados) {
		super(paisesClasificados, PREMIO_TRADICIONAL);
		
		numeroParaSorteo = getCodigoUnico();
	}

	public int verNumeroParaSorteo() {
		return numeroParaSorteo;
	}
}
