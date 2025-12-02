package its.rizzoli.FastLicense.DTO;

public class ImmagineDTO {

    private Integer id;
    private String url;
    private Integer argomentoId;
    private Integer capitoloId;

    public ImmagineDTO() {}

    public ImmagineDTO(Integer id, Integer argomentoId, Integer capitoloId, String fileName) {
        this.id = id;
        this.url = "/immagini/" + fileName;
        this.argomentoId = argomentoId;
        this.capitoloId = capitoloId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getArgomentoId() {
        return argomentoId;
    }

    public void setArgomentoId(Integer argomentoId) {
        this.argomentoId = argomentoId;
    }

    public Integer getCapitoloId() {
        return capitoloId;
    }

    public void setCapitoloId(Integer capitoloId) {
        this.capitoloId = capitoloId;
    }
}
