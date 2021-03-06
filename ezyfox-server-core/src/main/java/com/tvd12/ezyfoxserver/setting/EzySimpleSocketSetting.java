package com.tvd12.ezyfoxserver.setting;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "socket")
public class EzySimpleSocketSetting extends EzyAbstractSocketSetting implements EzySocketSetting {
    
    @XmlElement(name = "max-request-size")
    protected int maxRequestSize;
    
    public EzySimpleSocketSetting() {
        super();
        setPort(3005);
        setMaxRequestSize(32768);
        setCodecCreator("com.tvd12.ezyfox.codec.MsgPackCodecCreator");
    }
    
    @Override
    public Map<Object, Object> toMap() {
        Map<Object, Object> map = super.toMap();
        map.put("maxRequestSize", maxRequestSize);
        return map;
    }
}
