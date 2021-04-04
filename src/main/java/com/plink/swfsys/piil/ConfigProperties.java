package com.plink.swfsys.piil;

import com.plink.swfsys.piil.data.input.InputItemDescriptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties//(prefix = "mail")
public class ConfigProperties {

    private String hostName;
    private int port;
    private String from;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    // ------------------------------
    // TODO: Delete everything above

    public List<InputItemDescriptor> getInputItemDescriptors() {
        return inputItemDescriptors;
    }

    public void setInputItemDescriptors(List<InputItemDescriptor> inputItemDescriptors) {
        this.inputItemDescriptors = inputItemDescriptors;
    }

    private List<InputItemDescriptor> inputItemDescriptors;
}
