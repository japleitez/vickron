import dayjs from 'dayjs/esm';
import { CompanyState } from 'app/entities/enumerations/company-state.model';

export interface ICompany {
  id: number;
  country?: string | null;
  name?: string | null;
  state?: CompanyState | null;
  license?: string | null;
  start?: dayjs.Dayjs | null;
  end?: dayjs.Dayjs | null;
  email?: string | null;
  phone?: string | null;
  address?: string | null;
  billingPhone?: string | null;
  billingEmail?: string | null;
  billingAddress?: string | null;
  vatin?: string | null;
}

export type NewCompany = Omit<ICompany, 'id'> & { id: null };
