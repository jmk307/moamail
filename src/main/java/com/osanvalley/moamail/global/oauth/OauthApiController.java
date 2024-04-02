package com.osanvalley.moamail.global.oauth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osanvalley.moamail.global.config.CommonApiResponse;
import com.osanvalley.moamail.global.oauth.dto.GmailListResponseDto;
import com.osanvalley.moamail.global.oauth.dto.GmailResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "OAUTH API 테스트")
@RequestMapping("api/oauth")
public class OauthApiController {
    private final GoogleUtils googleUtils;

    @GetMapping("google/{accessToken}/{messageId}")
    @ApiOperation(value = "Gmail 메시지 하나 가져오기")
    public ResponseEntity<CommonApiResponse<GmailResponseDto>> showGmailMessage(
            @PathVariable String accessToken,
            @PathVariable String messageId) {
        return ResponseEntity.ok(CommonApiResponse.of(googleUtils.showGmailMessage(accessToken, messageId)));
    }

    @GetMapping("google/{accessToken}")
    @ApiOperation(value = "Gmail 메시지 리스트 가져오기")
    public ResponseEntity<CommonApiResponse<GmailListResponseDto>> showGmailMessages(
            @PathVariable String accessToken,
            @RequestParam(required = false) String nextPageToken) {
        return ResponseEntity.ok(CommonApiResponse.of(googleUtils.showGmailMessages(accessToken, nextPageToken)));
    }

    @PostMapping("google/{accessToken}")
    @ApiOperation(value = "Gmail 저장하기")
    public ResponseEntity<CommonApiResponse<String>> saveGmailMessages(
            @PathVariable String accessToken,
            @RequestParam(required = false) String nextPageToken) {
        return ResponseEntity.ok(CommonApiResponse.of(googleUtils.saveGmails(accessToken, nextPageToken)));
    }
}