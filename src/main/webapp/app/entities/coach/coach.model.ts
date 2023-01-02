import { Type } from 'app/entities/enumerations/type.model';

export interface ICoach {
  id: number;
  country?: string | null;
  name?: string | null;
  surname?: string | null;
  type?: Type | null;
  email?: string | null;
  phone?: string | null;
  resume?: string | null;
  contact?: string | null;
  lang?: string | null;
}

export type NewCoach = Omit<ICoach, 'id'> & { id: null };
