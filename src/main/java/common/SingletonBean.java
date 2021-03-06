/*
 * Copyright (c) 2019 - Present, Gopal S Akshintala
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * 	http://creativecommons.org/licenses/by-sa/4.0/
 */

package common;

import java.util.Objects;

public class SingletonBean {
    public static final SingletonBean INSTANCE = new SingletonBean();
    private String prop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (SingletonBean) o;
        return prop.equals(that.prop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prop);
    }
}
