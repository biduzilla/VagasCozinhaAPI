package com.example.vagascozinhaapi.service.impl;

import com.example.vagascozinhaapi.Exception.CvNaoEncontrado;
import com.example.vagascozinhaapi.Exception.RegrasNegocioException;
import com.example.vagascozinhaapi.Exception.UserNaoEncontrado;
import com.example.vagascozinhaapi.dto.CurriculumDto;
import com.example.vagascozinhaapi.dto.CurriculumDtoId;
import com.example.vagascozinhaapi.entidade.Curriculum;
import com.example.vagascozinhaapi.entidade.Enum.StatusCv;
import com.example.vagascozinhaapi.entidade.Usuario;
import com.example.vagascozinhaapi.repositorio.CurriculumRepository;
import com.example.vagascozinhaapi.repositorio.UserRepositorio;
import com.example.vagascozinhaapi.service.CurriculumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final UserRepositorio userRepositorio;

    @Override
    public CurriculumDtoId salvarCv(Integer id, CurriculumDto curriculumDto) {
        Usuario user = userRepositorio.findById(id).orElseThrow(UserNaoEncontrado::new);
        if (user.getCv() == StatusCv.NAO_CADASTRADO) {
            user.setCv(StatusCv.CADASTRADO);

            Curriculum cv = new Curriculum();
            cv.setNome(curriculumDto.getNome());
            cv.setEmailContatoCV(curriculumDto.getEmailContatoCV());
            cv.setSobre(curriculumDto.getSobre());
            cv.setSemestre(curriculumDto.getSemestre());
            cv.setExperiencias(curriculumDto.getExperiencias());
            cv.setQualificacoes(curriculumDto.getQualificacoes());
            cv.setTelefone(curriculumDto.getTelefone());
            cv.setUser(user);
            curriculumRepository.save(cv);

            CurriculumDtoId curriculumDtoId = new CurriculumDtoId();
            curriculumDtoId.setId(cv.getId());
            return curriculumDtoId;
        }
        throw new RegrasNegocioException("Curriculo já cadastrado, atualize ele");
    }



    @Override
    public CurriculumDto getCv(Integer id) {
        Usuario user = userRepositorio.findById(id).orElseThrow(UserNaoEncontrado::new);
        if(user.getCurriculum() == null) {
            throw new RegrasNegocioException("Curriculo não cadastrado, cadastre um");
        }
        Curriculum cv =  curriculumRepository.findById(user.getCurriculum().getId()).orElseThrow(CvNaoEncontrado::new);

        CurriculumDto curriculumDto = new CurriculumDto();
        curriculumDto.setNome(cv.getNome());
        curriculumDto.setEmailContatoCV(cv.getEmailContatoCV());
        curriculumDto.setTelefone(cv.getTelefone());
        curriculumDto.setSemestre(cv.getSemestre());
        curriculumDto.setSobre(cv.getSobre());
        curriculumDto.setExperiencias(cv.getExperiencias());
        curriculumDto.setQualificacoes(cv.getQualificacoes());
        return curriculumDto;
    }

    @Override
    public void updateCv(Integer id, CurriculumDto curriculumDto) {
        Usuario user = userRepositorio.findById(id).orElseThrow(UserNaoEncontrado::new);

        if(user.getCurriculum() == null) {
            throw new RegrasNegocioException("Curriculo não cadastrado, cadastre um");
        }

        Curriculum cv = curriculumRepository.findById(user.getCurriculum().getId()).orElseThrow(CvNaoEncontrado::new);
        cv.setNome(curriculumDto.getNome());
        cv.setEmailContatoCV(curriculumDto.getEmailContatoCV());
        cv.setTelefone(curriculumDto.getTelefone());
        cv.setSemestre(curriculumDto.getSemestre());
        cv.setSobre(curriculumDto.getSobre());
        cv.setExperiencias(curriculumDto.getExperiencias());
        cv.setQualificacoes(curriculumDto.getQualificacoes());
        curriculumRepository.save(cv);
    }

    @Override
    public void deleteCv(Integer id) {
        Usuario usuario = userRepositorio.findById(id).orElseThrow(UserNaoEncontrado::new);
        curriculumRepository.findById(usuario.getCurriculum().getId())
                .map(curriculum -> {
                    curriculumRepository.delete(curriculum);
                    return curriculum;
                })
                .orElseThrow(CvNaoEncontrado::new);
        usuario.setCv(StatusCv.NAO_CADASTRADO);
        userRepositorio.save(usuario);
    }
}