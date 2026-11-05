import java.util.ArrayList;

public class ObjCausa {
    //objeto para almacenar la información de las causas recibidas desde MariaDB
    //(debería haber creado esto para la 3.ª entrega y no ahora)

    //campos
    private int idCausa;
    private String nExpediente;
    private String tipoCausa;
    private boolean estado;
    private String fechaInicio;
    private String nombreActor;
    private int idActor;
    private String nombreDemandado;
    private int idDemandado;
    private int matriculaAbogado;
    private String juzgado;
    private String caratula;
    private String fechaSentencia;
    private String nSentencia;
    private String sentencia;
    private String resultado;
    private ArrayList<String> documentos;

    //constructor


    public ObjCausa(int idCausa, String nExpediente, String tipoCausa, boolean estado, String fechaInicio,
                    String nombreActor, int idActor, String demandado, int idDemandado, int matriculaAbogado,
                    String juzgado, String caratula, String fechaSentencia, String nSentencia, String sentencia,
                    String resultado) {
        this.idCausa = idCausa; //requerido
        this.nExpediente = nExpediente; //requerido
        this.tipoCausa = tipoCausa; //requerido
        this.estado = estado; //automatico
        this.fechaInicio = fechaInicio; //automatico
        this.nombreActor = nombreActor; //requerido
        this.idActor = idActor; //automatico
        this.nombreDemandado = demandado; //requerido
        this.idDemandado = idDemandado; //automatico
        this.matriculaAbogado = matriculaAbogado; //requerido
        this.juzgado = juzgado;// requerido
        this.caratula = caratula; //requerido
        if (fechaSentencia == null){
            this.fechaSentencia = ""; //editable
        } else {
            this.fechaSentencia = fechaSentencia; //editable
        }
        if (nSentencia == null) {
            this.nSentencia = ""; //editable
        }else{
            this.nSentencia = nSentencia; //editable
        }
        if (sentencia == null) {
            this.sentencia = ""; //editable
        }else{
            this.sentencia = sentencia; //editable
        }
        if (resultado == null) {
            this.resultado = ""; //editable
        } else {
            this.resultado = resultado; //editable
        }

    }

    //getters

    public int getIdCausa() {
        return idCausa;
    }

    public String getnExpediente() {
        return nExpediente;
    }

    public String getTipoCausa() {
        return tipoCausa;
    }

    public boolean getEstado() {
        return estado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public int getIdActor() {
        return idActor;
    }

    public String getNombreDemandado() {
        return nombreDemandado;
    }

    public int getIdDemandado() {
        return idDemandado;
    }

    public int getMatriculaAbogado() {
        return matriculaAbogado;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public String getCaratula() {
        return caratula;
    }

    public String getFechaSentencia() {
        return fechaSentencia;
    }

    public String getnSentencia() {
        return nSentencia;
    }

    public String getSentencia() {
        return sentencia;
    }

    public String getResultado() {
        return resultado;
    }

    public ArrayList<String> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<String> documentos) {
        this.documentos = documentos;
    }
}//fin class
