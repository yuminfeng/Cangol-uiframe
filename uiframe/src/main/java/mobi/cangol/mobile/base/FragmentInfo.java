/*
 * Copyright (c) 2013 Cangol
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
package mobi.cangol.mobile.base;

import android.os.Bundle;

public class FragmentInfo {
    protected final String tag;
    protected final Class<? extends BaseFragment> clazz;
    protected final Bundle args;

    public FragmentInfo(Class<? extends BaseFragment> clazz, String tag, Bundle args) {
        this.tag = tag;
        this.clazz = clazz;
        this.args = args;
    }

    public FragmentInfo(BaseFragment fragment) {
        tag = fragment.getTag();
        clazz = fragment.getClass();
        args = fragment.getArguments();
    }

    @Override
    public String toString() {
        return "FragmentInfo [tag=" + tag + ", clazz=" + clazz + ", args=" + args+ "]";
    }

}
