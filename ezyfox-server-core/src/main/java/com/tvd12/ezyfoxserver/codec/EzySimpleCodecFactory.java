package com.tvd12.ezyfoxserver.codec;

import com.tvd12.ezyfox.builder.EzyBuilder;
import com.tvd12.ezyfox.codec.EzyCodecCreator;
import com.tvd12.ezyfox.constant.EzyConstant;
import com.tvd12.ezyfox.reflect.EzyClasses;
import com.tvd12.ezyfoxserver.constant.EzyConnectionType;
import com.tvd12.ezyfoxserver.setting.EzySocketSetting;
import com.tvd12.ezyfoxserver.setting.EzyWebSocketSetting;

public class EzySimpleCodecFactory implements EzyCodecFactory {

	private final EzySocketSetting socketSettings;
	private final EzyWebSocketSetting websocketSettings;

	private final EzyCodecCreator socketCodecCreator;
	private final EzyCodecCreator websocketCodecCreator;
	
	public EzySimpleCodecFactory(Builder builder) {
		this.socketSettings = builder.socketSettings;
		this.websocketSettings = builder.websocketSettings;
		this.socketCodecCreator = newSocketCodecCreator();
		this.websocketCodecCreator = newWebsocketCodecCreator();
	}
	
	@Override
    public Object newEncoder(EzyConstant type) {
        if(type == EzyConnectionType.SOCKET)
            return socketCodecCreator.newEncoder();
        if(websocketCodecCreator != null)
            return websocketCodecCreator.newEncoder();
        return null;
    }
	
	@Override
	public Object newDecoder(EzyConstant type) {
		if(type == EzyConnectionType.SOCKET)
		    return socketCodecCreator.newDecoder(socketSettings.getMaxRequestSize());
		if(websocketCodecCreator != null)
		    return websocketCodecCreator.newDecoder(websocketSettings.getMaxFrameSize());
		return null;
	}
	
	private EzyCodecCreator newSocketCodecCreator() {
		return EzyClasses.newInstance(socketSettings.getCodecCreator());
	}
	
	private EzyCodecCreator newWebsocketCodecCreator() {
	    if(websocketSettings.isActive())
	        return EzyClasses.newInstance(websocketSettings.getCodecCreator());
	    return null;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder implements EzyBuilder<EzyCodecFactory> {
		private EzySocketSetting socketSettings;
		private EzyWebSocketSetting websocketSettings;
		
		public Builder socketSettings(EzySocketSetting settings) {
			this.socketSettings = settings;
			return this;
		}
		
		public Builder websocketSettings(EzyWebSocketSetting settings) {
			this.websocketSettings = settings;
			return this;
		}
		
		@Override
		public EzyCodecFactory build() {
			return new EzySimpleCodecFactory(this);
		}
	}
	
}
