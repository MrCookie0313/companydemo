package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Person {
    @JsonProperty("game")
    private GameDTO game;
    @JsonProperty("header")
    private HeaderDTO header;
    @JsonProperty("parameter")
    private ParameterDTO parameter;

    @NoArgsConstructor
    @Data
    public static class GameDTO {
        @JsonProperty("gameId")
        private String gameId;
        @JsonProperty("gameState")
        private String gameState;
    }

    @NoArgsConstructor
    @Data
    public static class HeaderDTO {
        @JsonProperty("frameIndex")
        private Integer frameIndex;
        @JsonProperty("function")
        private String function;
        @JsonProperty("messageId")
        private String messageId;
        @JsonProperty("messageType")
        private String messageType;
        @JsonProperty("tableId")
        private String tableId;
        @JsonProperty("timeStamp")
        private String timeStamp;
    }

    @NoArgsConstructor
    @Data
    public static class ParameterDTO {
        @JsonProperty("alarm")
        private AlarmDTO alarm;
        @JsonProperty("nvr")
        private NvrDTO nvr;

        @NoArgsConstructor
        @Data
        public static class AlarmDTO {
            @JsonProperty("alarmId")
            private String alarmId;
            @JsonProperty("alarmInfo")
            private String alarmInfo;
            @JsonProperty("bettingBox")
            private Integer bettingBox;
            @JsonProperty("debugInfo")
            private String debugInfo;
            @JsonProperty("type")
            private String type;
            @JsonProperty("uniqueId")
            private Long uniqueId;
        }

        @NoArgsConstructor
        @Data
        public static class NvrDTO {
            @JsonProperty("nvrChannel")
            private List<NvrChannelDTO> nvrChannel;
            @JsonProperty("nvrIp")
            private String nvrIp;
            @JsonProperty("subType")
            private String subType;

            @NoArgsConstructor
            @Data
            public static class NvrChannelDTO {
                @JsonProperty("channel")
                private Integer channel;
            }
        }
    }
}
