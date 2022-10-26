package tp_final;

public class AlbumWeb extends Album {
	private static String PREMIOS_WEB = "Camiseta oficial de la seleccion";
	
	private int codigoPromocional;
	
	
	public AlbumWeb(String[] paisesClasificados) {
		super(paisesClasificados, PREMIOS_WEB);
		
		codigoPromocional = getCodigoUnico();
	}



	public int verCodigoPromocional() {
	
	}
}
