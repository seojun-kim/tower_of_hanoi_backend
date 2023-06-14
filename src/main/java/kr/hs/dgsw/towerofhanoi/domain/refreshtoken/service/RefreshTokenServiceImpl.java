package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.service;

import io.jsonwebtoken.Claims;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.mapper.RefreshTokenMapper;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.repository.RefreshTokenRepository;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import kr.hs.dgsw.towerofhanoi.global.error.RefreshTokenExpirationException;
import kr.hs.dgsw.towerofhanoi.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper mapper;
    private final JwtUtils jwtUtils;

    @Override
    public RefreshTokenResponseDTO insert(RefreshTokenInsertDTO refreshTokenInsertDTO) {

        RefreshToken refreshToken = mapper.refreshTokenInsertDTOToRefreshToken(refreshTokenInsertDTO);
        refreshTokenRepository.save(refreshToken);

        return mapper.refreshTokenToRefreshTokenResponseDTO(refreshToken, refreshTokenInsertDTO.getMemberId());
    }

    @Override
    public RefreshTokenIssuedResponseDTO issued(RefreshTokenIssuedDTO refreshTokenIssuedDTO) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenIssuedDTO.getToken()).orElseThrow(() -> new RefreshTokenExpirationException(refreshTokenIssuedDTO.getToken()));
        Claims claims = jwtUtils.parseRefreshToken(refreshToken.getToken());

        Long memberId = jwtUtils.getMemberIdFormToken(refreshToken.getToken());

        List roles = (List) claims.get("roles");
        String username = claims.getSubject();

        String accessToken = jwtUtils.createAccessToken(memberId, username, roles);

        return mapper.accessTokenTokenIssuedResponseDTO(accessToken);
    }
}
