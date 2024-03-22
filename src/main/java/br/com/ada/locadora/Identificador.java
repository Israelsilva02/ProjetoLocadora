package br.com.ada.locadora;


import java.util.Objects;

public abstract class Identificador<T> {


    public abstract T valor();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identificador<?> that = (Identificador<?>) o;
        return Objects.equals(valor(), that.valor());
    }
}
