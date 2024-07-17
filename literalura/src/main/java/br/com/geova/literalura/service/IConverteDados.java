package br.com.geova.literalura.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
