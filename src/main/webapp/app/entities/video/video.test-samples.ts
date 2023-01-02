import { Type } from 'app/entities/enumerations/type.model';
import { VideoState } from 'app/entities/enumerations/video-state.model';

import { IVideo, NewVideo } from './video.model';

export const sampleWithRequiredData: IVideo = {
  id: 25330,
  code: 'users',
  type: Type['FITNESS'],
  state: VideoState['PUBLISHED'],
  name: 'silver withdrawal',
  lang: 'Cotton',
  url: 'https://dion.net',
};

export const sampleWithPartialData: IVideo = {
  id: 70764,
  code: 'Idaho secured',
  type: Type['FITNESS'],
  state: VideoState['ARCHIVED'],
  name: 'Legacy',
  lang: 'auxiliary',
  url: 'https://annalise.net',
};

export const sampleWithFullData: IVideo = {
  id: 79119,
  code: 'solution-oriented payment didactic',
  type: Type['FITNESS'],
  state: VideoState['ARCHIVED'],
  name: 'Cheese hack Jewelery',
  lang: 'Security Michigan',
  url: 'http://lily.biz',
};

export const sampleWithNewData: NewVideo = {
  code: 'multi-byte Republic',
  type: Type['WELLNESS'],
  state: VideoState['ARCHIVED'],
  name: 'Electronics',
  lang: 'transparent bypass',
  url: 'http://demarco.biz',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
