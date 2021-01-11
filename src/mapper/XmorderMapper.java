package mapper;

import com.mycls.ssm.entity.Xmorder;
import com.mycls.ssm.entity.XmorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmorderMapper {
    int countByExample(XmorderExample example);

    int deleteByExample(XmorderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(Xmorder record);

    int insertSelective(Xmorder record);

    List<Xmorder> selectByExample(XmorderExample example);

    Xmorder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") Xmorder record, @Param("example") XmorderExample example);

    int updateByExample(@Param("record") Xmorder record, @Param("example") XmorderExample example);

    int updateByPrimaryKeySelective(Xmorder record);

    int updateByPrimaryKey(Xmorder record);
}