package ink.moshuier.motse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class ScoreService {
  @Value("${spring.profiles.active}")
  private String profileActive;



}