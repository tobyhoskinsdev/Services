package examples.aaronhoskins.com.services;

public class ServiceEvents {
    private String serviceType;
    private String countFromService;

    public ServiceEvents() {
    }

    public ServiceEvents(String serviceType, String countFromService) {
        this.serviceType = serviceType;
        this.countFromService = countFromService;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCountFromService() {
        return countFromService;
    }

    public void setCountFromService(String countFromService) {
        this.countFromService = countFromService;
    }
}
