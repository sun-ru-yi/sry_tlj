package com.srytzj.domain;

import lombok.*;
import org.apache.tomcat.jni.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountVo extends User {
 
    private String token;

}