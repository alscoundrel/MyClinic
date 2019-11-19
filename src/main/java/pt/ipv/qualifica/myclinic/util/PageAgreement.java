package pt.ipv.qualifica.myclinic.util;

import java.util.HashMap;
import java.util.Map;

import pt.ipv.qualifica.myclinic.db.DbAgreement;

/**
 * 
 * @author Programador
 * Guarda valores a usar no site
 */
public class PageAgreement {
	
	/**
	 * 
	 * @author Programador
	 * os caminhos relativos da area de trabalho
	 */
	public static class Caminhos{
		public static final String ROOT = "/MyClinic";
		public static final String ROOT_PACIENTES = ROOT + "/" + DbAgreement.PacientesTable.TABLE_NAME;
		public static final String ROOT_MEDICOS = ROOT + "/" + DbAgreement.MedicosTable.TABLE_NAME;
		public static final String ROOT_CONSULTAS = ROOT + "/" + DbAgreement.ConsultasTable.TABLE_NAME;
		public static final String ROOT_UTILIZADORES = ROOT + "/" + DbAgreement.UtilizadoresTable.TABLE_NAME;
		public static final String ROOT_CONTACTOS = ROOT + "/contactos";
	}
	
	/**
	 * 
	 * @author Programador
	 * os nomes do proprietario do site
	 */
	public static class Propriedades{
		public static final String MARCA = "MyClinic";
	}
}
