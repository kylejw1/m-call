package com.kylejw.marketcall.server.opinions;

import java.util.Date;
import java.util.List;

import com.kylejw.marketcall.shared.model.Opinion;

public interface IOpinionMiner {
	List<Opinion> getOpinions(Date oldest, int max);
}
