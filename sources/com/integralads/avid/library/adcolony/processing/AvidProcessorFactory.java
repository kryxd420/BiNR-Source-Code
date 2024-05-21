package com.integralads.avid.library.adcolony.processing;

public class AvidProcessorFactory {
    private AvidSceenProcessor a = new AvidSceenProcessor(this.b);
    private AvidViewProcessor b = new AvidViewProcessor();

    public IAvidNodeProcessor getRootProcessor() {
        return this.a;
    }
}
