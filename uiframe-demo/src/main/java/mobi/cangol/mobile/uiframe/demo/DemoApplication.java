/**
 * Copyright (c) 2013 Cangol.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mobi.cangol.mobile.uiframe.demo;


import hugo.weaving.DebugLog;
import mobi.cangol.mobile.CoreApplication;
import mobi.cangol.mobile.logging.Log;


/**
 * @Description MobileApplication.java
 * @author Cangol
 * @date 2013-9-8
 */
@DebugLog
public class DemoApplication extends CoreApplication {
    @Override
    public void onCreate() {
        this.setDevMode(BuildConfig.DEBUG);
        super.onCreate();
        Log.setLogLevelFormat(android.util.Log.VERBOSE, false);
    }

}
