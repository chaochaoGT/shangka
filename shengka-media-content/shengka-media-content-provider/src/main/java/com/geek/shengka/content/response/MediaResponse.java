package com.geek.shengka.content.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**  视频详情返回对象
 * @author  xuxuelei
 * @data 2019-3-7
 */
@Data
public class MediaResponse  implements Serializable {
      private static final long serialVersionUID = 1L;
      /** 单个播放 **/
      private MediaInfo mediaInfo;
      /** 推荐视频 **/
      List<MediaInfo> listMedias = new ArrayList<MediaInfo>(0);
 
      

}
