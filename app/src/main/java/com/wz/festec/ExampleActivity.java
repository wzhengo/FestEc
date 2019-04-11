package com.wz.festec;

import com.wz.latte_core.activities.ProxyActivity;
import com.wz.latte_core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
