package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.mapper;

import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshToken refreshTokenInsertDTOToRefreshToken(RefreshTokenInsertDTO refreshTokenInsertDTO) {
        return RefreshToken.builder()
                .token(refreshTokenInsertDTO.getToken())
                .build();
    }

    @Override
    public RefreshTokenResponseDTO refreshTokenToRefreshTokenResponseDTO(RefreshToken refreshToken, Long memberId) {
        return RefreshTokenResponseDTO.builder()
                .token(refreshToken.getToken())
                .memberId(memberId)
                .build();
    }

    @Override
    public RefreshTokenIssuedResponseDTO accessTokenTokenIssuedResponseDTO(String accessToken) {
        return RefreshTokenIssuedResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public RefreshTokenInsertDTO createRefreshTokenInsertDTO(String refreshToken, Long memberId) {
        return RefreshTokenInsertDTO.builder()
                .token(refreshToken)
                .memberId(memberId)
                .build();
    }
}
