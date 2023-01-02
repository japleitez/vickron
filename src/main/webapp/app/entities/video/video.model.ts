import { ICategory } from 'app/entities/category/category.model';
import { ICoach } from 'app/entities/coach/coach.model';
import { Type } from 'app/entities/enumerations/type.model';
import { VideoState } from 'app/entities/enumerations/video-state.model';

export interface IVideo {
  id: number;
  code?: string | null;
  type?: Type | null;
  state?: VideoState | null;
  name?: string | null;
  lang?: string | null;
  url?: string | null;
  category?: Pick<ICategory, 'id'> | null;
  coach?: Pick<ICoach, 'id'> | null;
}

export type NewVideo = Omit<IVideo, 'id'> & { id: null };
