/**
 * 
 */
package pt.ipv.qualifica.myclinic.db;

/**
 * @author Programador
 *
 */
public class DbAgreement {
    public static class DataBase{
        public static final String DB_NAME = "myclinic";
        public static final String NO_CONNECT_DB = "Não tem ligação à base de dados!";
        public static final String NO_FOUND_DB = "Não encontrou a base de dados «" + DbAgreement.DataBase.DB_NAME + "»!";
    }
    public static class UtilizadoresTable {
        public static final String TABLE_NAME = "utilizadores";
        public static final String COLUMN_ID_NAME = "id_utilizador";
        public static final String COLUMN_UTILIZADOR_NAME = "utilizador";
        public static final String COLUMN_SENHA_NAME = "palavra_passe";
        public static final String COLUMN_NIVEL_NAME = "nivel";
        public static final String COLUMN_CONTROL_NAME = "ultimoAcesso";
    }
    
    public static class PacientesTable {
        public static final String TABLE_NAME = "pacientes";
        public static final String COLUMN_ID_NAME = "id_paciente";
        public static final String COLUMN_NOME_NAME = "nome";
        public static final String COLUMN_NIF_NAME = "nif";
        public static final String COLUMN_EMAIL_NAME = "email";
        public static final String COLUMN_CONTACTO_NAME = "contacto";
        public static final String COLUMN_MORADA_NAME = "morada";
    }
    
    public static class MedicosTable {
        public static final String TABLE_NAME = "medicos";
        public static final String COLUMN_ID_NAME = "id_medico";
        public static final String COLUMN_NOME_NAME = "nome";
        public static final String COLUMN_NIF_NAME = "nif";
        public static final String COLUMN_EMAIL_NAME = "email";
        public static final String COLUMN_CONTACTO_NAME = "contacto";
        public static final String COLUMN_MORADA_NAME = "morada";
        public static final String COLUMN_ID_ESPECIALIDADE = "FK_id_especialidade";
    }
    
    public static class EspecialidadesTable {
    	public static final String TABLE_NAME = "especialidades";
        public static final String COLUMN_ID_NAME = "id_especialidade";
        public static final String COLUMN_NOME_NAME = "descricao";
    }
    
    public static class ConsultasTable {
    	public static final String TABLE_NAME = "consultas";
        public static final String COLUMN_ID_NAME = "id_consulta";
        public static final String COLUMN_DATA_NAME = "data";
        public static final String COLUMN_PRESCRICAO = "prescricao";
        public static final String COLUMN_CANCELADA = "cancelada";
        public static final String COLUMN_PACIENTE_NAME = "FK_id_paciente";
        public static final String COLUMN_MEDICO_NAME = "FK_id_medico";
    }
}
