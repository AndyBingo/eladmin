package cn.mine.modules.system.service.impl;

import cn.mine.modules.system.domain.Nvr;
import cn.mine.utils.ValidationUtil;
import cn.mine.modules.system.repository.NvrRepository;
import cn.mine.modules.system.service.NvrService;
import cn.mine.modules.system.service.dto.NvrDTO;
import cn.mine.modules.system.service.dto.NvrQueryCriteria;
import cn.mine.modules.system.service.mapper.NvrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.mine.utils.PageUtil;
import cn.mine.utils.QueryHelp;

/**
* @author andy
* @date 2019-10-17
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NvrServiceImpl implements NvrService {

    @Autowired
    private NvrRepository nvrRepository;

    @Autowired
    private NvrMapper nvrMapper;

    @Override
    public Object queryAll(NvrQueryCriteria criteria, Pageable pageable){
        Page<Nvr> page = nvrRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(nvrMapper::toDto));
    }

    @Override
    public Object queryAll(NvrQueryCriteria criteria){
        return nvrMapper.toDto(nvrRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public NvrDTO findById(Long id) {
        Optional<Nvr> nvr = nvrRepository.findById(id);
        ValidationUtil.isNull(nvr,"Nvr","id",id);
        return nvrMapper.toDto(nvr.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NvrDTO create(Nvr resources) {
        return nvrMapper.toDto(nvrRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Nvr resources) {
        Optional<Nvr> optionalNvr = nvrRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalNvr,"Nvr","id",resources.getId());
        Nvr nvr = optionalNvr.get();
        nvr.setChannelCount(resources.getChannelCount());
        nvr.setIpAddr(resources.getIpAddr());
        nvr.setName(resources.getName());
        nvr.setPassword(resources.getPassword());
        nvr.setPort(resources.getPort());
        nvr.setUserName(resources.getUserName());
        nvrRepository.save(nvr);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        nvrRepository.deleteById(id);
    }
}