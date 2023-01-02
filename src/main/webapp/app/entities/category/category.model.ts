import { Type } from 'app/entities/enumerations/type.model';
import { YesNo } from 'app/entities/enumerations/yes-no.model';

export interface ICategory {
  id: number;
  code?: string | null;
  type?: Type | null;
  active?: YesNo | null;
  name?: string | null;
  label?: string | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
