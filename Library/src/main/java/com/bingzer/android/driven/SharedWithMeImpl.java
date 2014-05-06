/**
 * Copyright 2014 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.driven;

import com.bingzer.android.driven.contracts.Delegate;
import com.bingzer.android.driven.contracts.SharedWithMe;
import com.bingzer.android.driven.contracts.Task;

import static com.bingzer.android.driven.utils.AsyncUtils.doAsync;

/**
 * Created by Ricky on 5/5/2014.
 */
class SharedWithMeImpl implements SharedWithMe {

    private Driven driven;

    protected SharedWithMeImpl(Driven driven){
        this.driven = driven;
    }

    @Override
    public DriveFile title(DriveFile parent, String title) {
        return driven.first("'" + parent.getId() + "' in parents AND title = '" + title + "' AND sharedWithMe");
    }

    @Override
    public DriveFile title(String title) {
        return driven.first("title = '" + title + "' AND sharedWithMe");
    }

    @Override
    public void titleAsync(final DriveFile parent, final String title, Task<DriveFile> result) {
        doAsync(result, new Delegate<DriveFile>() {
            @Override
            public DriveFile invoke() {
                return title(parent, title);
            }
        });
    }

    @Override
    public void titleAsync(final String title, Task<DriveFile> result) {
        doAsync(result, new Delegate<DriveFile>() {
            @Override
            public DriveFile invoke() {
                return title(title);
            }
        });
    }
}
