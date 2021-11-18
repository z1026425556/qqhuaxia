package com.pengcheng.service.impl;

import com.pengcheng.dao.*;
import com.pengcheng.dao.sell.*;
import com.pengcheng.domain.*;
import com.pengcheng.domain.auth.User;
import com.pengcheng.domain.sell.*;
import com.pengcheng.service.ISellOrderService;
import com.pengcheng.service.IShiroService;
import com.pengcheng.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellOrderServiceImpl implements ISellOrderService {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private BondsmanMapper bondsmanMapper;

    @Autowired
    private GameNumberMapper gameNumberMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private SpiritMapper spiritMapper;

    @Autowired
    private EternalMapper eternalMapper;

    @Autowired
    private CombatPetMapper combatPetMapper;

    @Autowired
    private MountMapper mountMapper;

    @Autowired
    private PropMapper propMapper;

    @Autowired
    private IngotMapper ingotMapper;

    @Autowired
    private WordMoneyMapper wordMoneyMapper;

    @Autowired
    private GoldMapper goldMapper;

    @Autowired
    private SellOrderMapper sellOrderMapper;

    @Autowired
    private IShiroService shiroService;

    @Autowired
    private UserSellOrderMapper userSellOrderMapper;

    @Autowired
    private SellOrderBondsmanMapper sellOrderBondsmanMapper;

    @Autowired
    private SellOrderImgMapper sellOrderImgMapper;

    @Autowired
    private SellOrderVideoMapper sellOrderVideoMapper;

    @Override
    public ModelData listArea() {
        ModelData result = new ModelData();
        result.setCode("200");
        result.setMsg("查询成功！");
        result.data = areaMapper.list();
        return result;
    }

    @Override
    public ModelData listSonByAreaId(Long areaId) {
        ModelData result = new ModelData();
        result.code = "200";
        result.msg = "查询成功！";
        result.data = areaMapper.listSonByAreaId(areaId);
        return result;
    }

    @Override
    public ModelData listBondsman() {
        ModelData result = new ModelData();
        result.code = "200";
        result.msg = "查询成功！";
        result.data = bondsmanMapper.listAll();
        return result;
    }

    @Override
    public ModelData issuaSell(HttpServletRequest httpRequest, IssuaSellParam issuaSellParam) {
        ModelData result = new ModelData();
        SellOrder sellOrder = new SellOrder();
        sellOrder.setType(issuaSellParam.orderType);
        if(issuaSellParam.orderType.equals("0")){
            sellOrder.setTypeId(addGameNumber(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("1")){
            sellOrder.setTypeId(addEquipment(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("2")){
            sellOrder.setTypeId(addSpirit(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("3")){
            sellOrder.setTypeId(addEternal(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("4")){
            sellOrder.setTypeId(addCombatPet(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("5")){
            sellOrder.setTypeId(addMount(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("6")){
            sellOrder.setTypeId(addProp(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("7")){
            sellOrder.setTypeId(addIngot(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("8")){
            sellOrder.setTypeId(addWordMoney(issuaSellParam));
        }else if(issuaSellParam.orderType.equals("9")){
            sellOrder.setTypeId(addGold(issuaSellParam));
        }
        sellOrder.setTitle(issuaSellParam.title);
        sellOrder.setDescription(issuaSellParam.description);
        sellOrder.setPrice(issuaSellParam.price);
        sellOrder.setWxNumber(issuaSellParam.wxNumber);
        sellOrder.setCreateTime(LocalDateTime.now());
        //上架的有效时间
        //元宝、文钱、金币的上架有效时间为3天
        if(issuaSellParam.orderType.equals("7") || issuaSellParam.orderType.equals("8") || issuaSellParam.orderType.equals("9")){
            sellOrder.setExpireTime(LocalDateTime.now().plusDays(3));
        }else if(issuaSellParam.price < 500){
            sellOrder.setExpireTime(LocalDateTime.now().plusDays(15));
        }else if(issuaSellParam.price >= 500){
            sellOrder.setExpireTime(LocalDateTime.now().plusDays(30));
        }
        sellOrderMapper.addOne(sellOrder);
        User user = shiroService.findByAccessToken(httpRequest.getHeader("token"));
        UserSellOrder userSellOrder = new UserSellOrder();
        userSellOrder.setUserId(user.getId());
        userSellOrder.setSellOrderId(sellOrder.getId());
        userSellOrderMapper.addOne(userSellOrder);
        SellOrderBondsman sellOrderBondsman = new SellOrderBondsman();
        sellOrderBondsman.setSellOrderId(sellOrder.getId());
        sellOrderBondsman.setBondsmanId(issuaSellParam.bondsmanId);
        sellOrderBondsmanMapper.addOne(sellOrderBondsman);
        //图片
        if(issuaSellParam.imgPath != ""){
            File[] imgs = new File(FileUtil.sellOrderTempDir + issuaSellParam.imgPath).listFiles();
            new File(FileUtil.sellOrderImgCodeDir + sellOrder.getId()).mkdir();
            new File(FileUtil.sellOrderImgClassDir + sellOrder.getId()).mkdir();
            List<SellOrderImg> sellOrderImgs = new ArrayList<SellOrderImg>();
            for(File file : imgs){
                try{
                    FileUtil.copyFile(file, FileUtil.sellOrderImgCodeDir + sellOrder.getId());
                    FileUtil.copyFile(file, FileUtil.sellOrderImgClassDir + sellOrder.getId());
                } catch (IOException e) {
                    result.code = "500";
                    result.msg = "上传图片异常，请稍后重试！";
                    return result;
                }
                SellOrderImg sellOrderImg = new SellOrderImg();
                sellOrderImg.setSellOrderId(sellOrder.getId());
                sellOrderImg.setPath(sellOrder.getId() + "/" + file.getName());
                sellOrderImgs.add(sellOrderImg);
            }
            sellOrderImgMapper.addBatch(sellOrderImgs);
            FileUtil.delete(FileUtil.sellOrderTempDir + issuaSellParam.imgPath);
        }
        //视频
        if(issuaSellParam.videoPath != ""){
            new File(FileUtil.sellOrderVideoCodeDir + sellOrder.getId()).mkdir();
            new File(FileUtil.sellOrderVideoClassDir + sellOrder.getId()).mkdir();
            File videoFile = new File(FileUtil.sellOrderTempDir + issuaSellParam.videoPath).listFiles()[0];
            try {
                FileUtil.copyFile(videoFile, FileUtil.sellOrderVideoClassDir + sellOrder.getId());
                FileUtil.copyFile(videoFile, FileUtil.sellOrderVideoCodeDir + sellOrder.getId());
            } catch (IOException e) {
                result.code = "500";
                result.msg = "上传视频异常，请稍后重试！";
                return result;
            }
            SellOrderVideo sellOrderVideo = new SellOrderVideo();
            sellOrderVideo.setSellOrderId(sellOrder.getId());
            sellOrderVideo.setPath(sellOrder.getId() + "/" + videoFile.getName());
            sellOrderVideoMapper.addOne(sellOrderVideo);
            FileUtil.delete(FileUtil.sellOrderTempDir + issuaSellParam.videoPath);
        }
        result.code = "200";
        result.msg = "发布成功！";
        return result;
    }

    public Long addGameNumber(IssuaSellParam issuaSellParam){
        GameNumber gameNumber = new GameNumber();
        gameNumber.setAreaId(issuaSellParam.areaId);
        gameNumber.setAreaSonId(issuaSellParam.areaSonId);
        gameNumber.setProfession(issuaSellParam.gameNumberProfession);
        gameNumber.setBetter(issuaSellParam.gameNumberIsBetter);
        gameNumber.setLevel(issuaSellParam.gameNumberLevel);
        gameNumberMapper.addOne(gameNumber);
        return gameNumber.getId();
    }

    public Long addEquipment(IssuaSellParam issuaSellParam){
        Equipment equipment = new Equipment();
        equipment.setAreaId(issuaSellParam.areaId);
        equipment.setAreaSonId(issuaSellParam.areaSonId);
        equipment.setProfession(issuaSellParam.equipmentProfession);
        equipment.setGrade(issuaSellParam.equipmentGrade);
        equipment.setMeGroup(issuaSellParam.equipmentGroup);
        equipment.setLocation(issuaSellParam.equipmentLocation);
        equipmentMapper.addOne(equipment);
        return equipment.getId();
    }

    public Long addSpirit(IssuaSellParam issuaSellParam){
        Spirit spirit = new Spirit();
        spirit.setAreaId(issuaSellParam.areaId);
        spirit.setAreaSonId(issuaSellParam.areaSonId);
        spirit.setProfession(issuaSellParam.spiritProfession);
        spirit.setMeGroup(issuaSellParam.spiritGroup);
        spirit.setLevel(issuaSellParam.spiritLevel);
        spiritMapper.addOne(spirit);
        return spirit.getId();
    }

    public Long addEternal(IssuaSellParam issuaSellParam){
        Eternal eternal = new Eternal();
        eternal.setAreaId(issuaSellParam.areaId);
        eternal.setAreaSonId(issuaSellParam.areaSonId);
        eternal.setProfession(issuaSellParam.eternalProfession);
        eternal.setCategory(issuaSellParam.eternalCategory);
        eternal.setCategorySon(issuaSellParam.eternalCategorySon);
        eternalMapper.addOne(eternal);
        return eternal.getId();
    }

    public Long addCombatPet(IssuaSellParam issuaSellParam){
        CombatPet combatPet = new CombatPet();
        combatPet.setAreaId(issuaSellParam.areaId);
        combatPet.setAreaSonId(issuaSellParam.areaSonId);
        combatPet.setBetter(issuaSellParam.combatPetIsBetter);
        combatPet.setLevel(issuaSellParam.combatPetLevel);
        combatPet.setProp(issuaSellParam.combatPetProp);
        combatPetMapper.addOne(combatPet);
        return combatPet.getId();
    }

    public Long addMount(IssuaSellParam issuaSellParam){
        Mount mount = new Mount();
        mount.setAreaId(issuaSellParam.areaId);
        mount.setAreaSonId(issuaSellParam.areaSonId);
        mountMapper.addOne(mount);
        return mount.getId();
    }

    public Long addProp(IssuaSellParam issuaSellParam){
        Prop prop = new Prop();
        prop.setAreaId(issuaSellParam.areaId);
        prop.setAreaSonId(issuaSellParam.areaSonId);
        propMapper.addOne(prop);
        return prop.getId();
    }

    public Long addIngot(IssuaSellParam issuaSellParam){
        Ingot ingot = new Ingot();
        ingot.setAreaId(issuaSellParam.areaId);
        ingot.setAreaSonId(issuaSellParam.areaSonId);
        ingotMapper.addOne(ingot);
        return ingot.getId();
    }

    public Long addWordMoney(IssuaSellParam issuaSellParam){
        WordMoney wordMoney = new WordMoney();
        wordMoney.setAreaId(issuaSellParam.areaId);
        wordMoney.setAreaSonId(issuaSellParam.areaSonId);
        wordMoneyMapper.addOne(wordMoney);
        return wordMoney.getId();
    }

    public Long addGold(IssuaSellParam issuaSellParam){
        Gold gold = new Gold();
        gold.setAreaId(issuaSellParam.areaId);
        gold.setAreaSonId(issuaSellParam.areaSonId);
        goldMapper.addOne(gold);
        return gold.getId();
    }


}













































