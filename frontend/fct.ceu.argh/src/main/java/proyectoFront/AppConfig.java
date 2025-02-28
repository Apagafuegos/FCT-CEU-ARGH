package proyectoFront;

public class AppConfig {

	private static AppConfig instance;
	private String apiUrl;
	private String apiKey;

	private AppConfig() {
		this.apiUrl = "http://localhost:8080";
		this.apiKey = "EghAcof";
	}

	public static AppConfig getInstance() {
		if (instance == null) {
			instance = new AppConfig();
		}
		return instance;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getApiKey() {
		return apiKey;
	}
}