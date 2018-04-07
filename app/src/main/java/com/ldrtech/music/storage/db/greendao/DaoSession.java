package com.ldrtech.music.storage.db.greendao;

import com.ldrtech.music.model.Music;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * Created by xiaoh on 2018/3/25.
 */

public class DaoSession extends AbstractDaoSession {
    private final DaoConfig musicDaoConfig;

    private final MusicDao musicDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>    daoConfigMap) {
        super(db);

        musicDaoConfig = daoConfigMap.get(MusicDao.class).clone();
        musicDaoConfig.initIdentityScope(type);

        musicDao = new MusicDao(musicDaoConfig, this);

        registerDao(Music.class, musicDao);
    }

    public void clear() {
        musicDaoConfig.clearIdentityScope();
    }

    public MusicDao getMusicDao() {
        return musicDao;
    }
}
